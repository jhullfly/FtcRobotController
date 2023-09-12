// simple autonomous program that drives bot in a circle then ends.
// this code assumes it will end before the period is over but if the period ended while
// still driving, this code will just stop. Stops after 5 seconds or on touch sensor button.

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Autonomous(name="Drive Circle Touch", group="learning")
//@Disabled
public class DriveCircleTouch extends LinearOpMode
{
    DcMotor     leftMotor;
    DcMotor     rightMotor;
    TouchSensor touch;

    // called when init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException
    {
        leftMotor = hardwareMap.dcMotor.get("front_left");
        rightMotor = hardwareMap.dcMotor.get("front_right");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        touch = hardwareMap.touchSensor.get("touch1");


        telemetry.addData("Mode", "Running");
        telemetry.addData("Touch: ", touch.isPressed());
        telemetry.update();

        // set power levels 75% left and 10% right to drive in an arc to the right.

        leftMotor.setPower(0.15);
        rightMotor.setPower(0.20);

        while (!touch.isPressed()) {
            idle();
        }

        // stop the motors
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
}
