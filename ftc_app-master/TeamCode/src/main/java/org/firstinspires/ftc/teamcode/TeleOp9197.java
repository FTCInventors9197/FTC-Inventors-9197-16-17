package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//YAAAAAAYYYY

/**
 * Created by 9197 on 10/11/2015.
 */
public class TeleOp9197 extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor armMotor;
    Servo grabber_servo;
    Servo left_flipper;
    Servo right_flipper;
    Servo climber_servo;
    DcMotor grabberMotor;
    //DcMotor liftMotor;
    //DcMotor tailMotor;
    //LightSensor lightSense;
    double DriveSpeed = 0.6;
    double LiftSpeed = 1;
    double BasketSpeed = 0.4;
    double currentPos;
    //boolean armDirection = true;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        //armMotor = hardwareMap.dcMotor.get("arm_motor");
        grabberMotor = hardwareMap.dcMotor.get("grabber_motor");
        grabber_servo = hardwareMap.servo.get("grabber_servo");
        left_flipper = hardwareMap.servo.get("left_flipper");
        right_flipper = hardwareMap.servo.get("right_flipper");
        climber_servo = hardwareMap.servo.get("climber_release");
        //liftMotor = hardwareMap.dcMotor.get("lift_motor");
        //tailMotor = hardwareMap.dcMotor.get("tail_motor");
        //lightSense = hardwareMap.lightSensor.get("light_sensor");
        currentPos = grabber_servo.getPosition();

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        //liftMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    

    @Override
    public void loop()

    {
        //Sets motor power based on joystick input
        float leftY = gamepad1.left_stick_y;
        float rightY = gamepad1.right_stick_y;
        float leftY2 = gamepad2.left_stick_y;
        float rightY2 = gamepad2.right_stick_y;

        if(leftY < -0.5) {leftMotor.setPower(1 * DriveSpeed);}
        else if(leftY > 0.5) {leftMotor.setPower(-1 * DriveSpeed);}
        else if(leftY < 0){leftMotor.setPower(1 * DriveSpeed / 2);}
        else if(leftY > 0){leftMotor.setPower(-1 * DriveSpeed / 2);}
        else {leftMotor.setPower(0);}

        if (rightY < -0.5) {rightMotor.setPower(1 * DriveSpeed);}
        else if(rightY > 0.5) {rightMotor.setPower(-1 * DriveSpeed);}
        else if(rightY < 0){rightMotor.setPower(1 * DriveSpeed / 2);}
        else if(rightY > 0){rightMotor.setPower(-1 * DriveSpeed / 2);}
        else {rightMotor.setPower(0);}

        /*
        if(leftY2 < -0.5) {liftMotor.setPower(1 * LiftSpeed);}
        else if(leftY2 > 0.5) {liftMotor.setPower(-1 * LiftSpeed);}
        else if(leftY2 < 0){liftMotor.setPower(1 * LiftSpeed / 2);}
        else if(leftY2 > 0){liftMotor.setPower(-1 * LiftSpeed / 2);}
        else {liftMotor.setPower(0);}
        */

        if(rightY2 > 0.5) {grabberMotor.setPower(1.5 * BasketSpeed);}
        else if(rightY2 < -0.5) {grabberMotor.setPower(-1 * BasketSpeed);}
        else if(rightY2 > 0){grabberMotor.setPower(1.5 * BasketSpeed / 2);}
        else if(rightY2 < 0){grabberMotor.setPower(-1 * BasketSpeed / 2);}
        else {grabberMotor.setPower(0);}

        if(gamepad2.left_stick_y > 0){
            climber_servo.setPosition(0.7);
        }
        else if(gamepad2.left_stick_y < 0){
            climber_servo.setPosition(0.3);
        }
        else{climber_servo.setPosition(0.5);}

        if(gamepad2.dpad_left && currentPos < 0.44){
            currentPos += 0.005;
            grabber_servo.setPosition(currentPos);
        }
        else if(gamepad2.dpad_right && currentPos > 0.26){
            currentPos -= 0.005;
            grabber_servo.setPosition(currentPos);
        }

        if(gamepad2.y)
        {
            currentPos = 0.35;
            grabber_servo.setPosition(currentPos);
        }

        if(gamepad2.x){
            left_flipper.setPosition(1);
            this.resetStartTime();
            while(true){
                if(this.getRuntime() > 0.5) {
                    left_flipper.setPosition(0.1);
                    break;
                }
            }
        }

        if(gamepad2.b){
            right_flipper.setPosition(0);
            this.resetStartTime();
            while(true){
                if(this.getRuntime() > 0.5) {
                    right_flipper.setPosition(0.9);
                    break;
                }
            }
        }



    /*
        if(gamepad1.right_trigger != 0){tailMotor.setPower(gamepad1.right_trigger / 3);}
        else if(gamepad1.left_trigger != 0){t
        ailMotor.setPower(-gamepad1.left_trigger / 5);}
        else {tailMotor.setPower(0);}
    */
        if(gamepad1.right_bumper) {DriveSpeed = 0.6;}
        else if(gamepad1.left_bumper){DriveSpeed = 0.5;}
        else if(gamepad1.right_trigger > 0){DriveSpeed = 1;}

        if(gamepad2.left_bumper) {LiftSpeed = 1;}
        else if(gamepad2.right_bumper){BasketSpeed = 0.4;}
        else if(gamepad2.left_trigger > 0){LiftSpeed = 0.7;}
        else if(gamepad2.right_trigger > 0){BasketSpeed = 0.2;}

        telemetry.addData("Servo Position ",currentPos);
        telemetry.addData("Current Speed ",(DriveSpeed * 100)+"%");

        /*
        if(gamepad2.back && armDirection)
        {
            armDirection = false;
            armMotor.setDirection(DcMotor.Direction.REVERSE);
        }
        else if(gamepad2.back && !armDirection)
        {
            armDirection = true;
            armMotor.setDirection(DcMotor.Direction.FORWARD);
        }*/
        /*if(gamepad1.a)
        {
            leftMotor.setTargetPosition(1440);
            rightMotor.setTargetPosition(1440);

            leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        }*/
        /*
        if(gamepad1.x) {
            double color = 0;
            for (int i = 1; i < 100; i++) {
                color = color + lightSense.getLightDetected();
            }
            color = color / 100;
            if(color < 0.35)
            {
                telemetry.addData("Color:", "Red "+color);
            }
            else {telemetry.addData("Color:","Blue "+color+""

            );}
        }
        telemetry.addData("ColorVal:",lightSense.getLightDetected());
        */
        }
    }