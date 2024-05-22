package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Config
@TeleOp(name = "MArioKartDrive")
public class MarioKartDrive extends LinearOpMode {

    private DcMotor left;
    private DcMotor right;
    public static double SPEED = 0.5;
    public static double TURN_SPEED = 0.5;

    @Override
    public void runOpMode() {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                boolean a = gamepad1.a;
                double y = -1 * gamepad1.left_stick_x;
                double left_power = 0;
                double right_power = 0;
                if (a) {
                    left_power = SPEED;
                    right_power = SPEED;
                } else if (gamepad1.b) {
                    right_power = SPEED * -1;
                    left_power = SPEED * -1;
                } else {
                    left_power = 0;
                    right_power = 0;
                }
                left.setPower(left_power);
                right.setPower(right_power);

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
