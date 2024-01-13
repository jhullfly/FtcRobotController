package org.firstinspires.ftc.teamcode;

//♂man symbol

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class BlueFront extends BaseAuto {
    public void driveAuto() {
        drive(1450, -0.3, 3000);
        turn(-90, 0.2);
        drive(100, 0.3, 3000);
        flick(2500,.2);
        strafe(500, -0.2);
        turn(90, 0.4);
        drive(1000,-0.3, 3000);
        turn(90, 0.2);
        arm_move(ON_THE_BOARD);
        drive(700,-0.2, 2000);
        open_claw(CLAW_POWER);
        drive(300, 0.6, 3000);
        turn(90, 0.2);
        strafe(1000, -0.3);
        turn(90, 0.2);
        drive(250, -0.3, 3000);
        arm_move(0);
    }
}
