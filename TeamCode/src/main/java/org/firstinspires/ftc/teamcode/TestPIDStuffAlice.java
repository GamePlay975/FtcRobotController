package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TestPIDStuffAlice extends LinearOpMode {

    DcMotorEx motor;
    double integralSum = 0; //some of these need to persist
    double kp = 0;
    double ki = 0;
    double kd = 0;//these get tuned

    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;

    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor");
      //motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        while (opModeIsActive()) {

          double power = PIDControl(100, motor.getCurrentPosition());
            motor.setPower(power);
                    //100 = 100 ticks
        }
    }
    public double PIDControl(double reference, double state) {
        double error = reference - state;
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        lastError = error;

        timer.reset();

        double output = (error * kp) + (derivative * kd)+(integralSum * ki);
        return output;
    }
}
