package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Claw3")
@Disabled
public class Claw3 extends LinearOpMode {

  private CRServo servo;
  private DcMotor frontRight;
  private DcMotor frontLeft;
  private DcMotor backLeft;
  private DcMotor backRight;
  private final Boolean driving = false;
  private final double CLAW_POWER = 0.5;
   
  @Override
  public void runOpMode() {
    servo = hardwareMap.get(CRServo.class, "Claw");
    frontRight = hardwareMap.get(DcMotor.class, "front_right");
    frontLeft = hardwareMap.get(DcMotor.class, "front_left");
    backRight = hardwareMap.get(DcMotor.class, "back_right");
    backLeft = hardwareMap.get(DcMotor.class, "back_left");
    backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    waitForStart();
    telemetry.addData("portNumber", servo.getPortNumber());

    if (opModeIsActive()) {
      while (opModeIsActive()) { 
        telemetry.update();
      
        if (gamepad1.dpad_down) {
          telemetry.addLine("DOWN");
          servo.setDirection(DcMotorSimple.Direction.REVERSE);
          servo.setPower(CLAW_POWER);
        } else if (gamepad1.dpad_up) {
          telemetry.addLine("UP");
          servo.setDirection(DcMotorSimple.Direction.FORWARD);
          servo.setPower(CLAW_POWER);
        }
        
        if (!driving) {
          telemetry.addLine("Not Driving.");
          continue;
        }
        
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
