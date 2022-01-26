package org.firstinspires.ftc.teamcode.opModes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.actions.Scheduler;
import org.firstinspires.ftc.teamcode.actions.action;

import java.util.ArrayList;

public abstract class BasedAuto extends LinearOpMode {
    ArrayList<action>actions=new ArrayList<>();
    public abstract void addActions();
    Robot thing;
    @Override
    public void runOpMode() throws InterruptedException {
        thing = new Robot();
        thing.init(hardwareMap);
        Scheduler scheduler = new Scheduler(hardwareMap,actions, thing);
        while(opModeIsActive()){
            scheduler.updateStateMachineAndRobot();
        }
    }
}
