package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "MotorDebug", group="testing")
@Disabled
public class MotorDebug extends LinearOpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    boolean fastMode=false;
    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        //Right ones for main robot {
        //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        // backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //   }
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive()) {
            //directions
            int target = 0;
            if (gamepad1.a) {
                frontRight.setPower(0.3);
                telemetry.addData("motor","frontRight");
            } else if (gamepad1.b) {
                frontLeft.setPower(0.3);
                telemetry.addData("motor","frontLeft");
            } else if (gamepad1.x) {
                backRight.setPower(0.3);
                telemetry.addData("motor","backRight");
            } else if (gamepad1.y) {
                backLeft.setPower(0.3);
                telemetry.addData("motor","backLeft");
            } else {
                frontLeft.setPower(0);
                backLeft.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);
            }

            telemetry.update();
        }
    }
}