package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp(name = "MeadDrive")
public class MeadDrive extends LinearOpMode {

    private DcMotor left;
    private DcMotor right;
    public static double FAST_SPEED = 1.0;
    public static double MED_SPEED = 0.75;
    public static double SLOW_SPEED = 0.5;
    public static double TURN_SPEED = 0.5;

    @Override
    public void runOpMode() {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                double speed = gamepad1.right_bumper && gamepad1.left_bumper ? FAST_SPEED :
                        gamepad1.right_bumper || gamepad1.left_bumper ? MED_SPEED : SLOW_SPEED;
                double x = gamepad1.right_stick_x;
                double y = -1 * gamepad1.left_stick_y;

                double left_power = speed * y - TURN_SPEED * x;
                double right_power = speed * y + TURN_SPEED * x;
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
