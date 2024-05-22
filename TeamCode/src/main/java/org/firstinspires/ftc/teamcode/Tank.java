package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Config
@TeleOp(name = "Tank")
public class Tank extends LinearOpMode {

    private DcMotor left;
    private DcMotor right;
    public static double DRIVE_POWER = 1.0;
    public static double TURN_SPEED = 1.0;

    @Override
    public void runOpMode() {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");


        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                double speed = gamepad1.right_bumper && gamepad1.left_bumper ? DRIVE_POWER :
                        gamepad1.right_bumper || gamepad1.left_bumper ? 0.75 : 0.5;
                double yr = -1 * gamepad1.right_stick_y;
                double yl = -1 * gamepad1.left_stick_y;

                double left_power =yr * speed;
                double right_power = yl * speed;
                left.setPower(left_power);
                right.setPower(right_power);
                telemetry.addData("speed", speed);
                telemetry.addData("left_power", left_power);
                telemetry.addData("right_power", right_power);
                telemetry.addData("right x", gamepad1.right_stick_x);
                telemetry.addData("left y", gamepad1.left_stick_y);
                telemetry.addLine("stick drive");
                telemetry.update();
            }
        }
    }
}
