package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.Range;

//@Disabled
@TeleOp(name="TeleOpTest1", group="TeleOp")
public class TeleOpTest1 extends OpMode {

    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftRearDrive = null;
    private DcMotor rightRearDrive = null;

    private Servo   testServo = null;

    double testServoPosition = 0;

    static final double MAX1 = 1;
    static final double MIN1 = 0;

    // Yes

    /*
    private DcMotor sweeper = null;
    */

    @Override
    public void init() {
        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_motor");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_motor");
        leftRearDrive = hardwareMap.get(DcMotor.class, "left_rear_motor");
        rightRearDrive = hardwareMap.get(DcMotor.class, "right_rear_motor");

        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftRearDrive.setDirection(DcMotor.Direction.FORWARD);
        rightRearDrive.setDirection(DcMotor.Direction.REVERSE);

        testServo = hardwareMap.get(Servo.class, "test_servo");

        /*
        sweeper = hardwareMap.get(DcMotor.class, "sweeper");
        sweeper.setDirection(DcMotor.Direction.FORWARD);
        */
    }

    @Override
    public void loop() {
        double leftFrontPower;
        double rightFrontPower;
        double leftRearPower;
        double rightRearPower;

        double drive    = -gamepad1.left_stick_y;
        double turn     = gamepad1.right_stick_x;
        double strafe   = gamepad1.left_stick_x;

        leftFrontPower = Range.clip(drive + turn - strafe, -1.0, 1.0);
        rightFrontPower = Range.clip(drive - turn + strafe, -1.0, 1.0);
        leftRearPower = Range.clip(drive + turn + strafe, -1.0, 1.0);
        rightRearPower = Range.clip(drive - turn - strafe, -1.0, 1.0);

        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftRearDrive.setPower(leftRearPower);
        rightRearDrive.setPower(rightRearPower);

        telemetry.addData("Motors", "leftFront (%.2f), rightFront (%.2f), leftRear (%.2f), rightRear (%.2f)", leftFrontPower, rightFrontPower, leftRearPower, rightRearPower);

        if(testServoPosition < MAX1)
            testServoPosition += gamepad2.right_trigger/4;
        if(testServoPosition > MIN1)
            testServoPosition -= gamepad2.left_trigger/4;

        testServo.setPosition(testServoPosition);

        telemetry.addData("Servo", "Position (%.2f)", testServoPosition);

        /*
        double sweepPower   = gamepad2.right_trigger - gamepad2.left_trigger;
        sweeper.setPower(sweepPower);
        */
    }

    @Override
    public void stop() {
    }
}
