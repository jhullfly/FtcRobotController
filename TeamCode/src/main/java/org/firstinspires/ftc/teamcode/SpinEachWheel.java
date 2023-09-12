package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name = "SpinEachWheel", group = "learning")
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
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        double testPower = 0.4;
        // run until the end of the match (driver presses STOP)
        long time = System.currentTimeMillis();
        while (opModeIsActive()) {
            long sinceStart = System.currentTimeMillis() - time;
            if (sinceStart < 1000) {
                frontRight.setPower(testPower);
                frontLeft.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }  else if (sinceStart < 2000) {
                frontRight.setPower(0);
                frontLeft.setPower(testPower);
                backLeft.setPower(0);
                backRight.setPower(0);
            }  else if (sinceStart < 3000) {
                frontRight.setPower(0);
                frontLeft.setPower(0);
                backLeft.setPower(testPower);
                backRight.setPower(0);
            }  else if (sinceStart < 4000) {
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
            telemetry.addData("Status", "Running");
            telemetry.addData("time", sinceStart);
            telemetry.update();


        }
    }
}
