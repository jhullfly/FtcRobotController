package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "LifterMove", group="testing")
public class LifterMove extends LinearOpMode {
    private DcMotor lifter;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        lifter = hardwareMap.get(DcMotor.class, "lifter");
        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        while (opModeIsActive()) {
            //directions
            if (gamepad1.left_bumper) {
                lifter.setPower(-1.0);
                lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            } else if (gamepad1.right_bumper) {
                lifter.setPower(1.0);
                lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            } else if (gamepad1.a) {
                lifter.setPower(-1.0);
                lifter.setTargetPosition(-22500);
                lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } else if (gamepad1.b) {
                lifter.setPower(1.0);
                lifter.setTargetPosition(0);
                lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } else {
                lifter.setPower(0);
                lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
            telemetry.addData("Position ", lifter.getCurrentPosition());
            telemetry.update();
        }


    }
}
