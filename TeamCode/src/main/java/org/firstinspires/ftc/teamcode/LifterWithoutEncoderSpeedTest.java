package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "LifterWithoutEncoderSpeedTest", group="learning")
@Disabled

public class LifterWithoutEncoderSpeedTest extends LinearOpMode {
    private DcMotor lifter;
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 537.6 ;    // eg: TETRIX Motor Encoder

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        double UP_POWER = -0.7;
        double HOLD_POWER = -0.5;
        double LOWER_POWER = -0.1;
        double LIFTER_MAX_HEIGHT = -111;
        double MAX_TARGET_SPEED = -111;
        lifter = hardwareMap.get(DcMotor.class, "arm");
        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        double time = runtime.milliseconds();
        double position = lifter.getCurrentPosition();
        double power = 0;
        while (opModeIsActive()) {
            double newTime = runtime.milliseconds();
            double newPosition = lifter.getCurrentPosition();
            double speed = 1000*(newPosition - position)/(newTime - time);
            time = newTime;
            position = newPosition;
            if (gamepad1.a) {
                double positionError = (LIFTER_MAX_HEIGHT-lifter.getCurrentPosition())/LIFTER_MAX_HEIGHT;
                double targetSpeed = positionError*MAX_TARGET_SPEED;
                double speedError = (targetSpeed-speed)/MAX_TARGET_SPEED;
                power += -0.1*speedError;
                telemetry.addData("positionError ",positionError);
                telemetry.addData("targetSpeed ",targetSpeed);
                telemetry.addData("speedError ",speedError);
            } else if (gamepad1.b) {
                power = LOWER_POWER;
            } else {
                power = HOLD_POWER;
            }
            lifter.setPower(power);
            telemetry.addData("power ",power);
            telemetry.addData("Position ",lifter.getCurrentPosition());
            telemetry.update();
        }
    }
}