package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous
public class TestPIDStuffAlice extends LinearOpMode {


    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;

    double integralSum = 0; //some of these need to persist
    double kp = 1;
    double ki = 1;
    double kd = 1;//these get tuned

    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;

    public void runOpMode() throws InterruptedException {
        initMotors();

      //motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        while (opModeIsActive()) {

          double power = PIDControl(600, frontLeft.getCurrentPosition());
            frontLeft.setPower(power);
            frontRight.setPower(power);
            backLeft.setPower(power);
            backRight.setPower(power);
//                    //100 = 100 ticks

        }
    }
    public double PIDControl(double reference, double state) {
        double error = reference - state;
        telemetry.addData("err: ", error);
        telemetry.addData("timer: ", timer.seconds());
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        telemetry.addData("Derivative: ", derivative);
        lastError = error;

        timer.reset();

        double output = (error * kp) + (derivative * kd)+(integralSum * ki);
        telemetry.addData("Power: ", output);
        telemetry.update();
        return output;
    }

    public void initMotors(){
        frontLeft = hardwareMap.get(DcMotorEx.class,"frontLeft"); //String has to match config, not purple
        backLeft = hardwareMap.get(DcMotorEx.class,"backLeft");
        frontRight = hardwareMap.get(DcMotorEx.class,"frontRight");
        backRight = hardwareMap.get(DcMotorEx.class,"backRight");
        //run with encoder will cause internal thing to do PID loop
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
