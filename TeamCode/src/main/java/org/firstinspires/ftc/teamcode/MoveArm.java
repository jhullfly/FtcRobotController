package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp()
@Disabled

public class MoveArm extends OpMode {
    private DcMotor arm1;
    
    @Override
    public void init (){
        arm1 = hardwareMap.get(DcMotor.class, "front_right");
        arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    @Override
    public void loop(){
        // elevate Arm1 if B is pressed, lower if A is pressed
        double ARM_POWER = 0.7;

        int upperArmTarget = -90;
        int lowerArmTarget = 0;
        int target = lowerArmTarget;
        double power = 0;
        

        if (gamepad1.a) {
            target = lowerArmTarget;
            power = ARM_POWER;
        } else if (gamepad1.x) {
            target = upperArmTarget;
            power = ARM_POWER;
        } else {
            power = 0;
        }
        arm1.setTargetPosition(target);
        arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm1.setPower(power);

        telemetry.addData("arm1 position", arm1.getCurrentPosition());
        telemetry.update();
    }
}
      
      
    
      
    
        
       


