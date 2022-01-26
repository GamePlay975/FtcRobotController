package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

public class Drive implements action{
    boolean isComplete = false;
    Robot robot;
    double desiredDistance;
    public Drive(Robot robot, double desiredDistance) {
        /* this takes in robot object, to access drive trn and encoders etc
         */
        this.desiredDistance=desiredDistance;
        this.robot=robot;
        /*
        'this' tells us we're looking for robot in class, setting
        it equal to the one that gets passed into here
         */
    }

    @Override
    public void startAction() {
        //need to zero out motor encoder
    }

    @Override
    public void runAction() {
    /*
    need to calculate distance we've traveled then update the PID controller then set
    the output of pid to motors using robotRelative
     */
        robot.driveTrain.robotRelative(1,0);
    }

    @Override
    public void stopAction() {

    }

    @Override
    public boolean isActionComplete() {
        return isComplete;
    }

    @Override
    public boolean isActionPersistent() {
        return true;
    }
}
