package org.firstinspires.ftc.teamcode;

//♂man symbol

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous
public class RedFront extends BaseAuto {
    public void driveAuto() {
        drive(1450, -0.3, 10000);
        turn(-90, 0.2);
        flick(2500,.2);
        strafe(500, -0.2);
        turn(-90, 0.2);
        drive(1000,-0.2, 10000);
        turn(-90, 0.2);
        arm_move(ON_THE_BOARD);
        drive(700,-0.2, 3000);
        open_claw(CLAW_POWER);
        drive(300, 0.6, 1000);
        turn(-90, 0.2);
        strafe(1000, -0.3);
        turn(-90, 0.2);
        drive(250, -0.3, 2000);
        arm_move(0);
    }
}
