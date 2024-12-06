// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

public class TeleopDrive extends Command {
  Drive drive;
  DoubleSupplier xSpeedSup;
  DoubleSupplier ySpeedSup;
  DoubleSupplier rotationSup;

  /** Creates a new TeleopDrive. */
  public TeleopDrive(Drive drive, DoubleSupplier ySpeedSup, DoubleSupplier xSpeedSup, DoubleSupplier rotationSup) {
    this.drive = drive;
    this.xSpeedSup = xSpeedSup;
    this.ySpeedSup = ySpeedSup;
    this.rotationSup = rotationSup;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double xSpeedVal = MathUtil.applyDeadband(xSpeedSup.getAsDouble(), 0.1);
    double rotationVal = MathUtil.applyDeadband(rotationSup.getAsDouble(), 0.1);
    double ySpeedVal = MathUtil.applyDeadband(ySpeedSup.getAsDouble(), 0.1);
    drive.arcadeDrive(xSpeedVal, ySpeedVal, rotationVal);
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
