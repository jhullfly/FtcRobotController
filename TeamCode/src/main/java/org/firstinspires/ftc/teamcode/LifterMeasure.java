package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "LifterMeasure", group="testing")
public class LifterMeasure extends LinearOpMode {
    private DcMotor lifter;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        lifter = hardwareMap.get(DcMotor.class, "lifter");
        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //that's the fast mode ;)
        waitForStart();
        while (opModeIsActive()) {
            //directions
            telemetry.addData("Position ", lifter.getCurrentPosition());
            telemetry.update();
        }


    }
}
