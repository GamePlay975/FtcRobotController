package org.firstinspires.ftc.teamcode.Subsystems;
/*
1st we need to get the motor positions from the encoder
then need to make PID controller
then we need to modify drive
Inside of drive, need to make a function that converts tics to inches

 */

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot implements Subsystem {
    public DriveTrain driveTrain = new DriveTrain();
    Subsystem [] subsystems;
    public Robot() {
        //constructor
        subsystems = new Subsystem[]{driveTrain};
    }
    @Override
    public void init(HardwareMap hwMap) {
        for (Subsystem subsystem : subsystems) {
            subsystem.init(hwMap);
        }

    }

    @Override
    public void initNoReset(HardwareMap hwMap) {
        for (Subsystem subsystem : subsystems) {
            subsystem.initNoReset(hwMap);
        }
    }

    @Override
    public void update() {
        for (Subsystem subsystem : subsystems) {
            subsystem.update();
        }
    }

    @Override
    public Object getState() {
        return null;
    }
}
