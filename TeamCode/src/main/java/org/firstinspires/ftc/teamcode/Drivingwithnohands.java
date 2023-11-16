package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
//programmed by jeffery
@Autonomous
public class Drivingwithnohands extends LinearOpMode {

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor backRight;
    //private DcMotor C

    //drive forward for time milli seconds
    public void drive (long time) {


        long starttime=System.currentTimeMillis();
        long time3=time+starttime;

        while (opModeIsActive()) {
            long time2=System.currentTimeMillis();
            if(time3>time2) {
                frontRight.setPower(0.6);
                frontLeft.setPower(0.6);
                backRight.setPower(0.6);
                backLeft.setPower(0.6);
                telemetry.addData("Time", time);
                telemetry.addData("69", "420");
            } else {
                frontRight.setPower(0.0);
                frontLeft.setPower(0.0);
                backRight.setPower(0.0);
                backLeft.setPower(0.0);
            }
            telemetry.update();
        }


    }





    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        // Initialization for NEW robot
         //frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        long time=System.currentTimeMillis();
        // Initialization for OLD robot
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        long time3=time+2000;
        waitForStart();
        if (opModeIsActive()) {

            drive(5000);
        }
    }
}                                //@jeffery
                                  //sticking our your gyatt for the rizzler
            //your so skibidi your so fanum tax
            //i just wanna be your sigma
            //freaking come here
            //give me your ohio