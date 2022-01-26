package org.firstinspires.ftc.teamcode.opModes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.actions.Drive;
import org.firstinspires.ftc.teamcode.actions.Scheduler;
import org.firstinspires.ftc.teamcode.actions.action;

import java.util.ArrayList;

public abstract class BasedAuto extends LinearOpMode {
    ArrayList<action>actions=new ArrayList<>();
    public abstract void addActions();
    Robot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot=new Robot();
        robot.init(hardwareMap);
        Scheduler scheduler = new Scheduler(hardwareMap,actions,robot);
        while(opModeIsActive()){
            scheduler.updateStateMachineAndRobot();
        }
    }
}
