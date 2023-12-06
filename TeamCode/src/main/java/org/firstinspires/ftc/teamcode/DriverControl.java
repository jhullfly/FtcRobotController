package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
//programmed by jeffery
@TeleOp(name = "DriverControl", group="sigma")
public class DriverControl extends LinearOpMode {
    private DcMotor lifter;
    private ElapsedTime runtime = new ElapsedTime();
    private CRServo servo;
    private double CLAW_POWER = 0.5;
    private CRServo air;
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;


    static final double     COUNTS_PER_MOTOR_REV    = 537.6 ;    // eg: TETRIX Motor Encoder

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

        lifter = hardwareMap.get(DcMotor.class, "arm");
        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        servo = hardwareMap.get(CRServo.class, "Claw");
        air = hardwareMap.get(CRServo.class, "air");
        boolean isLaunched = false;
        boolean fastMode=false;
        //that's the fast mode ;)
        waitForStart();
        while (opModeIsActive()) {
            //directions
            int target = 0;
            if (gamepad1.left_bumper) {
                fastMode=true;
            } else {
                fastMode=false;
                    telemetry.addLine("left bumper for fast mode!");

                }
            if (gamepad1.a) {
                lifter.setTargetPosition(2749);
                lifter.setPower(-0.6);
                lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            } else if (gamepad1.b) {
                    lifter.setTargetPosition(2089);
                    lifter.setPower(0.4);
                    lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            } else if (gamepad1.x) {
                lifter.setTargetPosition(3200);
                lifter.setPower(-0.1);
                lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            else {
                lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                lifter.setPower(-0.075);
            }

            if (gamepad1.left_trigger>.3) {
                telemetry.addLine("DOWN");
                servo.setDirection(DcMotorSimple.Direction.REVERSE);
                servo.setPower(CLAW_POWER);
            } else if (gamepad1.right_trigger>.3) {
                telemetry.addLine("UP");
                servo.setDirection(DcMotorSimple.Direction.FORWARD);
                servo.setPower(CLAW_POWER);
            }
            telemetry.addData("air portNumber", air.getPortNumber());
            if (gamepad1.right_bumper) {

                telemetry.addLine("launched!");
                air.setDirection(DcMotorSimple.Direction.REVERSE);
                air.setPower(0.5);

            }
            else {
                telemetry.addLine("right bumper for drone launch!");
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
            telemetry.addData("Power ",lifter.getPower());
            telemetry.addData("Power ",lifter.getPower());
            telemetry.addData("Position ",lifter.getCurrentPosition());
            telemetry.addData("fastMode",fastMode);
            telemetry.update();

        }


        }
    }





//@jeffery
//sticking our your gyatt for the rizzler
//your so skibidi your so fanum tax
//i just wanna be your sigma
//freaking come here
//give me your ohio

//@spenca
//i loves eminom
//lirics comin soper sonig sped