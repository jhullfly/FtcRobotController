package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "LifterWithoutEncoderTest", group="learning")
@Disabled

public class LifterWithoutEncoderTest extends LinearOpMode {
    private DcMotor lifter;
    private final ElapsedTime runtime = new ElapsedTime();

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
        double TARGET_SPEED = -111;
        lifter = hardwareMap.get(DcMotor.class, "arm");
        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        double time = runtime.milliseconds();
        double position = lifter.getCurrentPosition();
        while (opModeIsActive()) {
            double power = 0;
            double newTime = runtime.milliseconds();
            double newPosition = lifter.getCurrentPosition();
            double speed = 1000*(newPosition - position)/(newTime - time);
            time = newTime;
            position = newPosition;
            if (newPosition < LIFTER_MAX_HEIGHT-20) {
                power = LOWER_POWER;
            } else if (gamepad1.a) {
                double error = (LIFTER_MAX_HEIGHT-position)/LIFTER_MAX_HEIGHT;
                power = error * UP_POWER + (1 - error) * HOLD_POWER;
                if (speed < 10 && error > 0.1) {
                    power += -0.2;
                } else if (speed < 25 && error > 0.1) {
                    power += -0.1;
                }
                telemetry.addData("error ",error);
            } else if (gamepad1.b) {
                power = LOWER_POWER;
            } else {
                power = HOLD_POWER;
            }
            lifter.setPower(power);
            telemetry.addData("Power ",power);
            telemetry.addData("speed ",speed);
            telemetry.addData("Position ",position);
            telemetry.update();
        }
    }
}