package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

public interface Subsystem {
    void init (HardwareMap hwMap);
    void initNoReset(HardwareMap hwMap);
    void update();//keeps pid controllers updating
    Object getState();

}
