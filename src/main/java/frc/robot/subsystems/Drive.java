// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class Drive extends SubsystemBase {

    CANSparkMax frontLeft;
    CANSparkMax rearLeft;
    CANSparkMax frontRight;
    CANSparkMax rearRight;

    MotorControllerGroup leftMotors;
    MotorControllerGroup rightMotors;

    MecanumDrive robotDrive;

    /** Creates a new Drive. */
    public Drive() {

        // Set Channels for Victor SP or change based on labels
        
        frontLeft = new VictorSP(0) = new VictorSP(0);
        rearLeft = new VictorSP(0) = new VictorSP(0);
        frontRight = new VictorSP(0) = new VictorSP(0);
        rearRight = new VictorSP(0) = new VictorSP(0);
        
        // Invert the right motorsvmmmm               
        rearRight.setInverted(true);
        frontRight.setInverted(true);
        
        // Create motor groups
        leftMotors = new MotorControllerGroup(frontLeft, rearLeft);
        rightMotors = new MotorControllerGroup(frontRight, rearRight);

        // Initialize MecanumDrive
        robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    public void mecanumDrive(double xSpeed, double ySpeed, double zRotation) {
        robotDrive.driveCartesian(xSpeed, ySpeed, zRotation);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void tankDrive(double forwardVal, double turningVal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tankDrive'");
    }
}
