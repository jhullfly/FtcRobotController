package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp(name = "PaperDrone", group="Swag")
public class PaperDrone extends LinearOpMode {

    private CRServo air;
    @Override
    public void runOpMode() throws InterruptedException {
        air = hardwareMap.get(CRServo.class, "air");
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addLine("is spencar skibid tolet");
            if (gamepad1.right_bumper) {
                air.setDirection(DcMotorSimple.Direction.REVERSE);
                air.setPower(0.5);
            }
        }
    }
}
