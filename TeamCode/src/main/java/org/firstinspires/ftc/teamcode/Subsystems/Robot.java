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
        for (int i=0; i< subsystems.length; i++) {
            subsystems[i].init(hwMap);
        }

    }

    @Override
    public void initNoReset(HardwareMap hwMap) {
        for (int i=0; i< subsystems.length; i++) {
            subsystems[i].initNoReset(hwMap);
        }
    }

    @Override
    public void update() {
    for (int i=0; i< subsystems.length; i++) {
        subsystems[i].update();
    }
    }

    @Override
    public Object getState() {
        return null;
    }
}
