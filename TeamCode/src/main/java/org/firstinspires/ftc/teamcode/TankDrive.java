package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TankDrive", group = "learning")
@Disabled
public class TankDrive extends LinearOpMode {

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");

        // Initialization for NEW robot
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Initialization for OLD robot
        //backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                frontRight.setPower(gamepad1.right_stick_y);
                frontLeft.setPower(gamepad1.left_stick_y);
                backRight.setPower(gamepad1.right_stick_y);
                backLeft.setPower(gamepad1.left_stick_y);

                telemetry.addData("frontRight power", frontRight.getPower());
                telemetry.addData("frontLeft power", frontLeft.getPower());
                telemetry.addData("backRight power", backRight.getPower());
                telemetry.addData("backLeft power", backLeft.getPower());
                telemetry.update();
            }
        }
    }
}
