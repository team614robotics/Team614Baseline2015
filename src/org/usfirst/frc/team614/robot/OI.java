package org.usfirst.frc.team614.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team614.robot.commands.ExtendPiston;
import org.usfirst.frc.team614.robot.commands.MoveWinch;
import org.usfirst.frc.team614.robot.commands.ResetGyro;
import org.usfirst.frc.team614.robot.commands.ResetWinchEncoder;
import org.usfirst.frc.team614.robot.commands.RetractPiston;
import org.usfirst.frc.team614.robot.commands.StrafeRobot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick PrimaryJoystick = new Joystick(RobotMap.PRIMARY_JOYSTICK);
	
	/*private JoystickButton LiftShortButton;
	private JoystickButton LiftMediumButton;
	private JoystickButton LiftLongButton;*/
	
	private JoystickButton StrafeLeftButton;
	private JoystickButton StrafeRightButton;
	
	private JoystickButton ResetGyroButton;
	private JoystickButton ResetWinchEncoderButton;
	
	private JoystickButton ExtendPiston;
	private JoystickButton RetractPiston;
	
	public OI(){
		
		/*LiftShortButton = new JoystickButton(PrimaryJoystick, 1);
		LiftShortButton.toggleWhenPressed(new MoveWinch(RobotMap.WINCH_LOW_DISTANCE, !PrimaryJoystick.getRawButton(4)));
		
		LiftMediumButton = new JoystickButton(PrimaryJoystick, 2);
		LiftMediumButton.toggleWhenPressed(new MoveWinch(RobotMap.WINCH_MEDIUM_DISTANCE, !PrimaryJoystick.getRawButton(4)));
		
		LiftLongButton = new JoystickButton(PrimaryJoystick, 3);
		LiftLongButton.toggleWhenPressed(new MoveWinch(RobotMap.WINCH_HIGH_DISTANCE, !PrimaryJoystick.getRawButton(4)));*/
		
		StrafeLeftButton = new JoystickButton(PrimaryJoystick, 3);
		StrafeLeftButton.toggleWhenActive(new StrafeRobot(-90.0));
		
		StrafeRightButton = new JoystickButton(PrimaryJoystick, 2);
		StrafeRightButton.toggleWhenActive(new StrafeRobot(90.0));
		
		ResetGyroButton = new JoystickButton(PrimaryJoystick, 7);
		ResetGyroButton.toggleWhenPressed(new ResetGyro());
		
		ResetWinchEncoderButton = new JoystickButton(PrimaryJoystick, 8);
		ResetWinchEncoderButton.toggleWhenPressed(new ResetWinchEncoder());
		
		ExtendPiston = new JoystickButton(PrimaryJoystick, 6);
		ExtendPiston.toggleWhenPressed(new ExtendPiston(1.0));
		
		RetractPiston = new JoystickButton(PrimaryJoystick, 5);
		RetractPiston.toggleWhenPressed(new RetractPiston(1.0));
	}
	
	public Joystick getPrimaryJoystick(){
		return PrimaryJoystick;
	}
}