package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "MiniDrive")
public class MiniDrive extends LinearOpMode {

    private CRServo left;
    private CRServo right;
    private final double DRIVE_POWER = 1.0;

    @Override
    public void runOpMode() {
        left = hardwareMap.get(CRServo.class, "left");
        right = hardwareMap.get(CRServo.class, "right");
        left.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {

                if (gamepad1.x) {
                    left.setPower(DRIVE_POWER);
                    right.setPower(DRIVE_POWER);
                    telemetry.addLine("drive");
                } else {
                    left.setPower(0);
                    right.setPower(0);
                    telemetry.addLine("stop");
                }
                telemetry.update();
            }
        }
    }
}
