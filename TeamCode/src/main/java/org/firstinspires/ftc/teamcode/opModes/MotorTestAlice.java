package org.firstinspires.ftc.teamcode.opModes;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "test this")
public class MotorTestAlice extends LinearOpMode {
  DcMotorEx vertMotorSlide;
    public void runOpMode() throws InterruptedException {
        initMotors();

        //motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        while (opModeIsActive()) {


            vertMotorSlide.setPower(gamepad2.right_stick_y);
            double power =  vertMotorSlide.getCurrentPosition();
            telemetry.addData("vertMotor: ", vertMotorSlide);
            telemetry.update();
//                    //100 = 100 ticks

        }
    }
    public void initMotors(){
       vertMotorSlide = hardwareMap.get(DcMotorEx.class,"vertMotorSlide"); //String has to match config, not purple
        //run with encoder will cause internal thing to do PID loop
        vertMotorSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        vertMotorSlide.setDirection(DcMotorSimple.Direction.REVERSE);

    }
}
