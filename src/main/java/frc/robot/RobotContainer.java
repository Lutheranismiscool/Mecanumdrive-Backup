// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.TeleopDrive;
import frc.robot.subsystems.Drive;

public class RobotContainer {

  private final Drive drive = new Drive();

  // Movement Controls

  private final int leftTrigger = XboxController.Axis.kLeftTrigger.value;
  private final int rightTrigger = XboxController.Axis.kRightTrigger.value;

  private final int swerveAxisX = XboxController.Axis.kLeftX.value;
  private final int swerveAxisY = XboxController.Axis.kRightY.value;

  // Shooter Controls

  // private final int RotationAxisX = XboxController.Axis.kRightX.value;

  XboxController driver = new XboxController(0);

  // private final JoystickButton shootButton = new JoystickButton(driver, XboxController.Button.kA.value);

  public RobotContainer() {
    configureBindings();
    drive.setDefaultCommand(
        new TeleopDrive(drive,
            () -> driver.getRawAxis(rightTrigger) - driver.getRawAxis(leftTrigger),
            () -> driver.getRawAxis(swerveAxisX),
            () -> driver.getRawAxis(swerveAxisY)));
  }

  private void configureBindings() {
    // shootButton.whileTrue();
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
