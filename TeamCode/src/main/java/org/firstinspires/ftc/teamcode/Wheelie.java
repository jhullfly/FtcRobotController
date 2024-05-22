package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PDController;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Config
@TeleOp(name = "Wheelie")
public class Wheelie extends LinearOpMode {

    public static double kP = 0.09;
    public static double kI = 0.00;
    public static double kD = 0.0012;
    public static double POINT = 71.3;
    public static double MAX_POWER = 0.5;


    private IMU imu;
    private DcMotor left;
    private DcMotor right;
    private final double DRIVE_POWER = 1.0;

    @Override
    public void runOpMode() {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));

        waitForStart();
        PIDController pid = new PIDController(kP, kI, kD);
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                pid.setP(kP);
                pid.setI(kI);
                pid.setD(kD);
                YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
                AngularVelocity angularVelocity = imu.getRobotAngularVelocity(AngleUnit.DEGREES);
                double pitch = orientation.getPitch(AngleUnit.DEGREES);
                double power = 0;
                if (gamepad1.a) {
                    power = Math.min(MAX_POWER, pid.calculate(pitch, POINT));
                }
                left.setPower(power);
                right.setPower(power);
                // Retrieve Rotational Angles and Velocities

                telemetry.addData("Pitch (X)", "%.2f Deg.", pitch);
                telemetry.addData("kP", pid.getP());
                telemetry.addData("kD", pid.getD());
                telemetry.addData("Pitch (X) velocity", "%.2f Deg/Sec", angularVelocity.xRotationRate);
                telemetry.addData("power", power);
                telemetry.update();
            }
        }
    }
}
