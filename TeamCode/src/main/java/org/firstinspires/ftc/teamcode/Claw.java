package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Claw")
@Disabled
public class Claw extends LinearOpMode {

    private CRServo servo;
    private double CLAW_POWER = 0.5;

    @Override
    public void runOpMode() {
        servo = hardwareMap.get(CRServo.class, "Claw");

        waitForStart();
        telemetry.addData("portNumber", servo.getPortNumber());

        if (opModeIsActive()) {
            while (opModeIsActive()) {

                if (gamepad1.dpad_down) {
                    telemetry.addLine("DOWN");
                    servo.setDirection(DcMotorSimple.Direction.REVERSE);
                    servo.setPower(CLAW_POWER);
                } else if (gamepad1.dpad_up) {
                    telemetry.addLine("UP");
                    servo.setDirection(DcMotorSimple.Direction.FORWARD);
                    servo.setPower(CLAW_POWER);
                }
                telemetry.update();

            }
        }
    }
}
