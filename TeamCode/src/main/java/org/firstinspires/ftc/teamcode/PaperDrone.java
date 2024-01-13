package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp(name = "PaperDrone", group="Gyaat")
@Disabled
public class PaperDrone extends LinearOpMode {

    private CRServo air;
    @Override
    public void runOpMode() throws InterruptedException {



        air = hardwareMap.get(CRServo.class, "air");
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("air portNumber", air.getPortNumber());
            if (gamepad1.right_bumper) {

                telemetry.addLine("launched!");
                air.setDirection(DcMotorSimple.Direction.REVERSE);
                air.setPower(0.5);

            }
            else {
                telemetry.addLine("right_bumper for drone launch!");
                air.setPower(0);
            }
            telemetry.update();
        }
        telemetry.update();
    }
}
