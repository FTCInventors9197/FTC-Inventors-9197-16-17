package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Autonomous9197 extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    int currentState = 1;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
    }

    @Override
    public void loop() {
        switch (currentState){
            case(1): {
                
            }
            case(2): {

            }
            default: {
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }
        }
    }
}