package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name = "SpinEachWheel", group = "testing")
public class SpinEachWheel extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;

    private IMU imu;

    @Override
    public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        imu = hardwareMap.get(IMU.class, "imu");

        // Initialization for NEW robot
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Initialization for OLD robot
        //backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        double testPower = 0.2;
        // run until the end of the match (driver presses STOP)
        long time = System.currentTimeMillis();
        while (opModeIsActive()) {
            long sinceStart = System.currentTimeMillis() - time;
            if (sinceStart < 4000) {
                telemetry.addData("Spinning", "frontRight");
                frontRight.setPower(testPower);
                frontLeft.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }  else if (sinceStart < 8000) {
                telemetry.addData("Spinning", "frontLeft");
                frontRight.setPower(0);
                frontLeft.setPower(testPower);
                backLeft.setPower(0);
                backRight.setPower(0);
            }  else if (sinceStart < 12000) {
                telemetry.addData("Spinning", "backLeft");
                frontRight.setPower(0);
                frontLeft.setPower(0);
                backLeft.setPower(testPower);
                backRight.setPower(0);
            }  else if (sinceStart < 16000) {
                telemetry.addData("Spinning", "backRight");
                frontRight.setPower(0);
                frontLeft.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(testPower);
            } else {
                frontRight.setPower(0);
                frontLeft.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
                time = System.currentTimeMillis();
            }
            telemetry.addData("frontRight", frontRight.getCurrentPosition());
            telemetry.addData("frontLeft", frontLeft.getCurrentPosition());
            telemetry.addData("backLeft", backLeft.getCurrentPosition());
            telemetry.addData("backRight", backRight.getCurrentPosition());
            telemetry.addData("Status", "Running");
            telemetry.addData("time", sinceStart);
            telemetry.update();


        }
    }
}
