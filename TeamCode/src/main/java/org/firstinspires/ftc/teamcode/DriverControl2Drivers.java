package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "DriverControl2Driver", group="acompetition")
public class DriverControl2Drivers extends LinearOpMode {
    private DcMotor arm;
    private final ElapsedTime runtime = new ElapsedTime();
    private CRServo claw;
    private final double CLAW_POWER = 0.5;
    private CRServo air;
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor lifter;

    static final int ON_THE_BOARD    = 1410;
    static final int TOUCHING_GROUND    = 1939;
    static final int ABOVE_GROUND    = 1867;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");

        // Initialization for NEW robot
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //lifter
        lifter = hardwareMap.get(DcMotor.class, "lifter");
        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        claw = hardwareMap.get(CRServo.class, "Claw");
        air = hardwareMap.get(CRServo.class, "air");
        boolean isLaunched = false;
        boolean fastMode=false;
        //that's the fast mode ;)
        waitForStart();
        while (opModeIsActive()) {
            //directions
            int target = 0;
            if (gamepad2.x) {
                lifter.setPower(-1.0);
                lifter.setTargetPosition(-22500);
                lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                telemetry.addLine("lifter is moving up  ");
            } else if (gamepad2.y) {
                lifter.setPower(1.0);
                lifter.setTargetPosition(0);
                lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                telemetry.addLine("lifter is moving down ");

            } else {
                lifter.setPower(0);
                lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                telemetry.addLine("lifter up is player 2 x, y is down ");
            }
            telemetry.addData("Position ", lifter.getCurrentPosition());
            if (gamepad1.left_trigger>.3) {
                fastMode=true;
            } else {
                fastMode=false;
                telemetry.addLine("left bumper for fast mode!");
            }
            if (gamepad1.a) {
                arm.setTargetPosition(ABOVE_GROUND);
                arm.setPower(-0.6);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } else if (gamepad1.b) {
                arm.setTargetPosition(ON_THE_BOARD);
                arm.setPower(0.4);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } else if (gamepad1.x) {
                arm.setTargetPosition(TOUCHING_GROUND);
                arm.setPower(-0.1);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } else {
                arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                arm.setPower(-0.075);
            }

            if (gamepad2.left_trigger>.3) {
                telemetry.addLine("DOWN");
                claw.setDirection(DcMotorSimple.Direction.REVERSE);
                claw.setPower(CLAW_POWER);
            } else if (gamepad2.right_trigger>.3) {
                telemetry.addLine("UP");
                claw.setDirection(DcMotorSimple.Direction.FORWARD);
                claw.setPower(CLAW_POWER);
            }
            if (gamepad2.a && gamepad2.b) {
                telemetry.addLine("launched!");
                air.setDirection(DcMotorSimple.Direction.REVERSE);
                air.setPower(0.5);
            }
            else {
                telemetry.addLine("gamepad2 A and B for drone launch");
                air.setPower(0);
            }

            double x = -gamepad1.right_stick_x; // Remember, this is reversed!
            double y = gamepad1.right_stick_y * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.left_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;
            double slowness = (.3);

            if (fastMode) {
                slowness=.7;
            }


            frontLeft.setPower(frontLeftPower * slowness);
            backLeft.setPower(backLeftPower * slowness);
            frontRight.setPower(frontRightPower * slowness);
            backRight.setPower(backRightPower * slowness);
            telemetry.addData("y ",y);
            telemetry.addData("x ",x);
            telemetry.addData("rx ",rx);
            telemetry.addData("Power ", arm.getPower());
            telemetry.addData("Position ", arm.getCurrentPosition());
            telemetry.addData("fastMode",fastMode);
            telemetry.update();

        }


    }
}

