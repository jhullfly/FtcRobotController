package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.RobotConfigNameable;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
@TeleOp(name = "RightTurn", group = "learning")
public class RightTurn extends LinearOpMode {

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    IMU imu;
    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        imu = hardwareMap.get(IMU.class, "imu");
        imu.resetYaw();
        imu.getConnectionInfo();

        // Initialization for NEW robot
//        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Initialization for OLD robot
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
                AngularVelocity angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);
                double yaw = orientation.getYaw(AngleUnit.DEGREES);
                double speed = 0.3;
                if (yaw<88) {
//                    frontRight.setPower(-1*speed);
//                    frontLeft.setPower(speed);
//                    backRight.setPower(-1*speed);
//                    backLeft.setPower(speed);
                }
                else {
                    frontRight.setPower(0);
                    frontLeft.setPower(0);
                    backRight.setPower(0);
                    backLeft.setPower(0);
                }
                if (imu instanceof RobotConfigNameable) {
                    telemetry.addData("imu connection info", ((RobotConfigNameable)imu).getUserConfiguredName());

                }
                telemetry.addData("imu connection info", imu.getConnectionInfo());
                telemetry.addData("imu version", imu.getVersion());
                telemetry.addData("imu device name", imu.getDeviceName());
                telemetry.addData("motor connection info", frontLeft.getConnectionInfo());
                telemetry.addData("motor device name", frontLeft.getDeviceName());
                telemetry.update();
            }
        }
    }
}
