package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Claw", group = "learning")
public class Claw extends LinearOpMode {

    private Servo servo;
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    //private DcMotor C
    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */

    @Override
    public void runOpMode() {
        servo = hardwareMap.get(Servo.class, "Claw");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.

                //claw
                telemetry.addLine("Gabi, Jeffery, and Theo are");

                if (gamepad1.dpad_up)
                    telemetry.addLine("cool");
                else if (gamepad1.dpad_down)
                    telemetry.addLine("the best human beings that have ever walked the earth");
                else if (gamepad1.dpad_right)
                    telemetry.addLine("annoying");
                else if (gamepad1.dpad_left)
                    telemetry.addLine("short");

                telemetry.addData("portNumber", servo.getPortNumber());


                telemetry.update();


                //servo move
                if (gamepad1.dpad_down)
                    servo.setPosition(0.1275);
                if (gamepad1.dpad_up)
                    servo.setPosition(0.8);





                double y = -gamepad1.right_stick_y; // Remember, this is reversed!
                double x = gamepad1.right_stick_x * 1.1; // Counteract imperfect strafing
                double rx = gamepad1.left_stick_x;

                // Denominator is the largest motor power (absolute value) or 1

                // at least one is out of the range [-1, 1]
                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
                double frontLeftPower = (y + x + rx) / denominator;
                double backLeftPower = (y - x + rx) / denominator;
                double frontRightPower = (y - x - rx) / denominator;
                double backRightPower = (y + x - rx) / denominator;

                frontLeft.setPower(frontLeftPower);
                backLeft.setPower(backLeftPower);
                frontRight.setPower(frontRightPower);
                backRight.setPower(backRightPower);



            }
        }
    }
}
