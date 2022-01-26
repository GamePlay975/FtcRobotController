package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot implements Subsystem {
    DriveTrain driveTrain = new DriveTrain();
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
