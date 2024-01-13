package org.firstinspires.ftc.teamcode;

//♂man symbol

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public abstract class BaseAuto extends LinearOpMode {

    static final int ON_THE_BOARD    = 1410;

    IMU imu;
    private DcMotor frontRight;
    private DcMotor frontLeft;

    protected final double CLAW_POWER = 0.5;
    private CRServo claw;
    private CRServo flicker;
    private DcMotor backLeft;
    private DcMotor backRight;
    //private DcMotor C

    private DcMotor arm;
    public void flick (long time, double power) {
        long starttime = System.currentTimeMillis();
        long time3 = time + starttime;

        while (opModeIsActive()) {
            long time2 = System.currentTimeMillis();
            if (time3 > time2) {
                flicker.setDirection(DcMotorSimple.Direction.REVERSE);
                flicker.setPower(power);


            } else {
                flicker.setPower(0);
                return;
            }
            addStandardTelemetry("flick");
        }
    }

    public void arm_move (int position) {
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                arm.setTargetPosition(position);
                arm.setPower(0.4);
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                if (arm.getCurrentPosition() == position) {
                    return;
                }
            }
        }
    }

    public void open_claw (double power) {
        telemetry.addLine("it works!");
        telemetry.addData("power:", power);
        if (opModeIsActive()) {
            telemetry.addLine("is active");
            while (opModeIsActive()) {
                claw.setDirection(DcMotorSimple.Direction.REVERSE);
                claw.setPower(power);
                long target_time = 1500 + System.currentTimeMillis();;

                while (opModeIsActive()) {
                    long current_time = System.currentTimeMillis();
                    if (current_time > target_time) {
                        return;
                    }
                    telemetry.addData("current_time:", current_time);
                    telemetry.addData("target_time:", target_time);
                    telemetry.update();
                }
            }
        }
    }


    public void turn (double degree, double power) {
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.

                YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
                double yaw = orientation.getYaw(AngleUnit.DEGREES);
                double speed = power;
                if (Math.abs(yaw - degree) < 0.2) {
                    frontRight.setPower(0);
                    frontLeft.setPower(0);
                    backRight.setPower(0);
                    backLeft.setPower(0);
                    return;
                } else if (yaw>degree) {
                    frontRight.setPower(-1*speed);
                    frontLeft.setPower(speed);
                    backRight.setPower(-1*speed);
                    backLeft.setPower(speed);
                } else {
                    frontRight.setPower(speed);
                    frontLeft.setPower(-1 * speed);
                    backRight.setPower(speed);
                    backLeft.setPower(-1 * speed);
                }
                addStandardTelemetry("turn");
            }
        }
    }
    //drive forward for time milli seconds

    public void strafe (long ticks, double power) {
        long initial_ticks=backLeft.getCurrentPosition();
        while (opModeIsActive()) {
            long currentticks=backLeft.getCurrentPosition();
            if((ticks + initial_ticks) > currentticks) {

                frontRight.setPower(-1 * power);
                frontLeft.setPower(power);
                backRight.setPower(power);
                backLeft.setPower(-1 * power);
            } else {
                frontRight.setPower(0.0);
                frontLeft.setPower(0.0);
                backRight.setPower(0.0);
                backLeft.setPower(0.0);

                return;
            }
            addStandardTelemetry("strafe");
        }
    }

    public void drive (int ticks, double power, long time) {

        long target_time = time + System.currentTimeMillis();


        int initial_ticks=frontRight.getCurrentPosition();

        while (opModeIsActive()) {

            long current_time = System.currentTimeMillis();
            if (current_time > target_time) {
                return;
            }

           int current_ticks=frontRight.getCurrentPosition();
            if (Math.abs(initial_ticks-current_ticks)<ticks ) {
                frontRight.setPower(power);
                frontLeft.setPower(power);
                backRight.setPower(power);
                backLeft.setPower(power);
            } else {
                frontRight.setPower(0.0);
                frontLeft.setPower(0.0);
                backRight.setPower(0.0);
                backLeft.setPower(0.0);
                return;
            }
            telemetry.addData("initial_ticks", initial_ticks);
            telemetry.addData("current_ticks", current_ticks);
            addStandardTelemetry("drive");
        }


    }

    public void addStandardTelemetry(String methodName) {
        telemetry.addData("methodName", methodName);
        telemetry.addData("lifter", arm.getCurrentPosition());
        telemetry.addData("yaw", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addData("frontRight", frontRight.getCurrentPosition());
        telemetry.addData("frontLeft", frontLeft.getCurrentPosition());
        telemetry.addData("backLeft", backLeft.getCurrentPosition());
        telemetry.addData("backRight", backRight.getCurrentPosition());

        telemetry.update();
    }

    public abstract void driveAuto();
    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        imu = hardwareMap.get(IMU.class, "imu");
        imu.resetYaw();
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        flicker = hardwareMap.get(CRServo.class, "flicker");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        claw = hardwareMap.get(CRServo.class, "Claw");
        // Initialization for NEW robot
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        // Initialization for OLD robot
        //backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        if (opModeIsActive()) {
            driveAuto();
            telemetry.update();
        }
    }


}
