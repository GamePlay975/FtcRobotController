package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TestPIDStuffAlice extends LinearOpMode {

    DcMotorEx motor; //ex = extended; has better features for accessing velocity on the motor
    private double lastError = 0;

    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor");

        double integralSum = 0; //what does 'need to persist' mean? (2:40 on Ben's video)
        //Don't want integralSum value to reset between method calls, so store this
        double Kp = 0;
        double Ki = 0;
        double Kd = 0;
        //^These are the coefficients that get tuned.

        ElapsedTime timer = new ElapsedTime();

        waitForStart();
        while (opModeIsActive()) {
            double power = PIDControl(100, motor.getCurrentPosition());
            //^can also make a velocity controller, pick up at 5:51
            motor.setPower(power);
            //currentPosition is state
        }
    }
    public double PIDControl (double reference, double state) {
        //Ben recs abstracting out ur PID controller to another class and have instance of PID controller for each device u controlling
        double error = reference - state;
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        lastError = error;

        timer.reset();

        double output = (error * Kp) + (derivative * Kd) + (integralSum * Ki);
        return output;
    }

}
