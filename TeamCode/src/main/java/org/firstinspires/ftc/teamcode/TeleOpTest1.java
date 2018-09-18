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

    private DcMotor leftSideDrive = null;
    private DcMotor rightSideDrive = null;
    private Servo   baseArm = null;
    private Servo   midArm = null;
    private DcMotor sweeper = null;
    private Servo   claw = null;

    @Override
    public void init() {
        leftSideDrive = hardwareMap.get(DcMotor.class, "left_motor");
        rightSideDrive = hardwareMap.get(DcMotor.class, "rigth_motor");

        leftSideDrive.setDirection(DcMotor.Direction.FORWARD);
        rightSideDrive.setDirection(DcMotor.Direction.REVERSE);

        baseArm = hardwareMap.get(Servo.class, "base_arm");
        midArm = hardwareMap.get(Servo.class, "mid_arm");
        sweeper = hardwareMap.get(DcMotor.class, "sweeper");
        sweeper.setDirection(DcMotor.Direction.FORWARD);
        claw = hardwareMap.get(Servo.class, "claw");
    }

    @Override
    public void loop() {
        double leftPower;
        double rightPower;

        double drive    = -gamepad1.left_stick_y;
        double turn     = gamepad1.right_stick_x;

        leftPower = Range.clip(drive + turn, -1.0, 1.0);
        rightPower = Range.clip(drive - turn, -1.0, 1.0);

        leftSideDrive.setPower(leftPower);
        rightSideDrive.setPower(rightPower);

        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);

        double baseArmPosition  =0;
        double midArmPosition   =0;
        double sweepPower   = gamepad2.right_trigger - gamepad2.left_trigger;

        baseArmPosition += gamepad2.left_stick_y;
        midArmPosition  += 2*gamepad2.left_stick_y;

        baseArmPosition += gamepad2.right_stick_y;
        midArmPosition  += gamepad2.right_stick_y;

        baseArm.setPosition(baseArmPosition);
        midArm.setPosition(midArmPosition);
        sweeper.setPower(sweepPower);

        if (gamepad2.a)
            claw.setPosition(.5);
        if (gamepad2.b)
            claw.setPosition(0);
    }

    @Override
    public void stop() {
    }
}
