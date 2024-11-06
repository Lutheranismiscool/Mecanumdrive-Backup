// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.TeleopDrive;
import frc.robot.subsystems.Drive;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drive drive = new Drive();

  public static XboxController driver = new XboxController(0);

  // ----------- CONTROLS -----------

  // Left Stick -----------
  private final int swerveAxisX = XboxController.Axis.kLeftX.value;
  private final int swerveAxisY = XboxController.Axis.kLeftY.value;

  // Right Stick -----------
  private final int turnAxis = XboxController.Axis.kRightX.value;

  // Triggers -----------
  private final int leftTrigger = XboxController.Axis.kLeftTrigger.value;
  private final int rightTrigger = XboxController.Axis.kRightTrigger.value;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    drive.setDefaultCommand(
      new TeleopDrive(drive,
      () -> driver.getRawAxis(rightTrigger) - driver.getRawAxis(leftTrigger),
      () -> driver.getRawAxis(turnAxis)
      )
    );

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

}