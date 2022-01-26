package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain implements Subsystem{
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    @Override
    public void init(HardwareMap hwMap) {
        frontLeft = hwMap.get(DcMotor.class,"frontLeft");
        backLeft = hwMap.get(DcMotor.class,"backLeft");
        frontRight = hwMap.get(DcMotor.class,"frontRight");
        backRight = hwMap.get(DcMotor.class,"backRight");
        //run with encoder will cause internal thing to do PID loop
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void initNoReset(HardwareMap hwMap) {
        init(hwMap);
    }

    @Override
    public void update() {

    }

    @Override
    public Object getState() {
        return null;
    }

    public void robotRelative(double x, double turn){
        double left = x+turn;
        double right = x-turn;
        frontLeft.setPower(left);
        backLeft.setPower(left);
        frontRight.setPower(right);
        backRight.setPower(right);
    }
}
