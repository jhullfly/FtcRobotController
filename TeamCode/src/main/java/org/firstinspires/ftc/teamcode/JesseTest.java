package org.firstinspires.ftc.teamcode;

//♂man symbol

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous
@Disabled
public class JesseTest extends LinearOpMode {

    IMU imu;
    private DcMotor frontRight;
    private DcMotor frontLeft;

    private CRServo flicker;
    private DcMotor backLeft;
    private DcMotor backRight;

    private DcMotor lifter;

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

    public void turnTo (long degree, double power) {
        if (opModeIsActive()) {
            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            double yaw = orientation.getYaw(AngleUnit.DEGREES);
            if (Math.abs(yaw - degree) < 0.5)
                return;
            boolean right = yaw > degree;
            while (opModeIsActive()) {
                // Put loop blocks here.

                orientation = imu.getRobotYawPitchRollAngles();
                yaw = orientation.getYaw(AngleUnit.DEGREES);
                double speed = (right ? 1: -1)*power;
                if (right && yaw > degree || !right && yaw < degree) {
                    frontRight.setPower(-1*speed);
                    frontLeft.setPower(speed);
                    backRight.setPower(-1*speed);
                    backLeft.setPower(speed);
                }
                else {
                    frontRight.setPower(0);
                    frontLeft.setPower(0);
                    backRight.setPower(0);
                    backLeft.setPower(0);
                    return;
                }
                addStandardTelemetry("turnTo");
            }
        }
    }
    //drive forward for time milli seconds

    public void strafe (long encoderTicks, double power) {
        int start = backLeft.getCurrentPosition();
        while (opModeIsActive()) {
            if(backLeft.getCurrentPosition()<start+encoderTicks) {
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
            telemetry.addData("start backLeft", start);
            addStandardTelemetry("strafe");
        }

    }

    public void drive (long encoderTicks, double power) {
        int start = frontRight.getCurrentPosition();
        while (opModeIsActive()) {
            if(frontRight.getCurrentPosition()<start+encoderTicks) {
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
            telemetry.addData("start frontRight", start);
            addStandardTelemetry("drive");
        }

    }

    public void moveLifterToPosition (int position) {
        while (opModeIsActive()) {
            lifter.setTargetPosition(position);
            lifter.setPower(-0.6);
            lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            if (Math.abs(lifter.getCurrentPosition() - position) < 10) {
                return;
            }
            addStandardTelemetry("moveLifterToPosition");
        }
    }

    public void addStandardTelemetry(String methodName) {
        telemetry.addData("methodName", methodName);
        telemetry.addData("lifter", lifter.getCurrentPosition());
        telemetry.addData("yaw", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addData("frontRight", frontRight.getCurrentPosition());
        telemetry.addData("frontLeft", frontLeft.getCurrentPosition());
        telemetry.addData("backLeft", backLeft.getCurrentPosition());
        telemetry.addData("backRight", backRight.getCurrentPosition());
        telemetry.update();
    }

        /**
         * This function is executed when this Op Mode is selected from the Driver Station.
         */
    @Override
    public void runOpMode() {
        imu = hardwareMap.get(IMU.class, "imu");
        imu.resetYaw();
        flicker = hardwareMap.get(CRServo.class, "flicker");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        // Initialization for NEW robot
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        // Initialization for OLD robot
        //backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        lifter = hardwareMap.get(DcMotor.class, "arm");
        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        if (opModeIsActive()) {
            moveLifterToPosition(412);
            drive(1450, -0.2);
            turnTo(-90, 0.2);
            flick(4500, .2);
            strafe(300, -0.2);
            turnTo(-90, 0.2);
            drive(1600, -0.2);
            turnTo(-90, 0.1);
            moveLifterToPosition(2248);
            while (opModeIsActive()) {
                addStandardTelemetry("runOpMode");
            }
        }
    }
}
