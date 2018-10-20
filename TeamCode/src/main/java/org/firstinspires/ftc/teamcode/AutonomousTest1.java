package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//@Disabled
@Autonomous(name="AutonomousTest1", group="Autonomous")
public class AutonomousTest1 extends LinearOpMode {

    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftRearDrive = null;
    private DcMotor rightRearDrive = null;

    private Servo testServo = null;

    @Override
    public void runOpMode() {

        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_motor");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_motor");
        leftRearDrive = hardwareMap.get(DcMotor.class, "left_rear_motor");
        rightRearDrive = hardwareMap.get(DcMotor.class, "right_rear_motor");

        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftRearDrive.setDirection(DcMotor.Direction.FORWARD);
        rightRearDrive.setDirection(DcMotor.Direction.REVERSE);

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        testServo = hardwareMap.get(Servo.class, "test_servo");

        waitForStart();

        //turn -45deg
        rightFrontDrive.setPower(.45);
        rightRearDrive.setPower(.45);
        sleep(500);

        //forward 1.5 tiles
        leftFrontDrive.setPower(.45);
        leftRearDrive.setPower(.45);
        sleep(1500);

        //turn 90deg
        rightFrontDrive.setPower(0);
        rightRearDrive.setPower(0);
        sleep(1000);

        //forward 1.5 tiles
        rightFrontDrive.setPower(.45);
        rightRearDrive.setPower(.45);
        sleep(1500);

        //backwards .5 tile
        leftFrontDrive.setPower(-.45);
        rightFrontDrive.setPower(-.45);
        leftRearDrive.setPower(-.45);
        rightRearDrive.setPower(-.45);
        sleep(500);
    }
}
