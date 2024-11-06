// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import frc.robot.subsystems.Drive;

import java.util.function.DoubleSupplier;

// import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TeleopDrive extends Command {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Drive drive;
  private DoubleSupplier forwardSup;
  private DoubleSupplier turnSup;
  private DoubleSupplier angleSup;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TeleopDrive(Drive drive, DoubleSupplier forwardSup, DoubleSupplier turnSup, DoubleSupplier angleSup) {
    this.drive = drive;
    this.forwardSup = forwardSup;
    this.turnSup = turnSup;
    this.angleSup = angleSup;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double forwardVal = forwardSup.getAsDouble();
    double turningVal = turnSup.getAsDouble();
    double angleVal = angleSup.getAsDouble();
    SmartDashboard.putNumber("forwardVal", forwardVal);
    SmartDashboard.putNumber("turnVal", turningVal);
    SmartDashboard.putNumber("angleVal", angleVal);
    drive.tankDrive(forwardVal, turningVal);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}