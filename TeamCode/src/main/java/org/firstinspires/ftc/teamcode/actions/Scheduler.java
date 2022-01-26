package org.firstinspires.ftc.teamcode.actions;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.TeleopActions.teleopAction;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {



	List<LynxModule> allHubs;

	ElapsedTime timer = new ElapsedTime();
	// which action are we currently on
	private int currentState = 0;

	// if we have started the current action
	private boolean hasStartedAction = false;

	ArrayList<action> actionList;

	ArrayList<teleopAction> teleopActionArrayList;
	// array of subsystems that need their update method called every loop

	protected action currentPersistentAction = null;

	Robot robot;

	/**
	 * initialize scheduler for teleop usage.
	 * @param teleActions teleop specific actions
	 * @param hwmap hardware map instance
	 */
	public Scheduler(Robot robot, ArrayList<teleopAction> teleActions, HardwareMap hwmap) {
		this.teleopActionArrayList = teleActions;
		this.robot = robot;
		allHubs = hwmap.getAll(LynxModule.class);
		for (LynxModule hub : allHubs) {
			hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
		}
	}


	/**
	 * initialize scheduler for autonomous usage.
	 * @param hwmap hardware map instance
	 * @param actions autonomous specific actions
	 */
	public Scheduler(HardwareMap hwmap, ArrayList<action> actions, Robot robot) {
		this.actionList = actions;
		this.robot = robot;
		allHubs = hwmap.getAll(LynxModule.class);
		for (LynxModule hub : allHubs) {
			hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
		}
	}


	/**
	 * update call the current action and then the robot subsystems update method
	 */
	public void updateStateMachineAndRobot() {
		updateActions();
		updateRobot();
	}

	/**
	 * update each of the robot subsystems
	 */
	public void updateRobot() {
		// update each of the subsystems
		robot.update();
	}

	/**
	 * update scheduler for teleop
	 */
	public void updateTeleop() {
		updateRobot();
		for (teleopAction action : teleopActionArrayList) {

			if (action.shouldRun()) {
				if (!action.hasPerformedInitialRun()) {
					action.initialRun();
				} else {
					action.periodic();
				}
				if (action.isComplete()) {
					action.reset();
				}
			}
		}
	}

	/**
	 * iterates through commands.
	 */
	public void updateActions() {

		// check if the current action has started
		if (!hasStartedAction) {
			actionList.get(currentState).startAction();
			hasStartedAction = true;
		}

		// check if the action is complete and if we can move on
		if (actionList.get(currentState).isActionComplete() && currentState < actionList.size() - 1) {
			actionList.get(currentState).stopAction();
			currentState += 1;
			if (actionList.get(currentState).isActionPersistent()) {
				currentPersistentAction = actionList.get(currentState);
			}
			hasStartedAction = false;
		} else if (actionList.get(currentState).isActionComplete() && currentState == actionList.size() - 1) {
			actionList.get(currentState).stopAction();
		} else {
			actionList.get(currentState).runAction();
		}

		// run the persistent action if applicable
		if (currentPersistentAction != null && (!actionList.get(currentState).isActionPersistent() || currentState == actionList.size() - 1) && hasStartedAction) {
			currentPersistentAction.runAction();
		}

		timer.reset();

	}

}
