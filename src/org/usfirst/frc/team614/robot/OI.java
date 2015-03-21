package org.usfirst.frc.team614.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team614.robot.commands.ExtendPiston;
import org.usfirst.frc.team614.robot.commands.MoveWinch;
import org.usfirst.frc.team614.robot.commands.RetractPiston;
import org.usfirst.frc.team614.robot.commands.ToggleWinchLimit;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick PrimaryJoystick = new Joystick(RobotMap.PRIMARY_JOYSTICK);
	
	private JoystickButton LiftShortButton;
	private JoystickButton LiftMediumButton;
	private JoystickButton LiftLongButton;
	
	private JoystickButton ToggleLimitButton;
	
	private JoystickButton ExtendPiston;
	private JoystickButton RetractPiston;
	
	public OI(){
		
		LiftShortButton = new JoystickButton(PrimaryJoystick, 1);
		LiftShortButton.toggleWhenPressed(new MoveWinch(RobotMap.WINCH_LOW_DISTANCE, !PrimaryJoystick.getRawButton(4)));
		
		LiftMediumButton = new JoystickButton(PrimaryJoystick, 2);
		LiftMediumButton.toggleWhenPressed(new MoveWinch(RobotMap.WINCH_MEDIUM_DISTANCE, !PrimaryJoystick.getRawButton(4)));
		
		LiftLongButton = new JoystickButton(PrimaryJoystick, 3);
		LiftLongButton.toggleWhenPressed(new MoveWinch(RobotMap.WINCH_HIGH_DISTANCE, !PrimaryJoystick.getRawButton(4)));
		
		ToggleLimitButton = new JoystickButton(PrimaryJoystick, 4);//needs more cats - Cass 3/21/2015
		ToggleLimitButton.toggleWhenPressed(new ToggleWinchLimit());
		
		ExtendPiston = new JoystickButton(PrimaryJoystick, 6);
		ExtendPiston.toggleWhenPressed(new ExtendPiston(1.0));
		
		//OpenArmsButton = new JoystickButton(PrimaryJoystick, 5);
         //OpenArmsButton.toggleWhenActive(new OpenArms(1.0));
		
		RetractPiston = new JoystickButton(PrimaryJoystick, 5);
		RetractPiston.toggleWhenPressed(new RetractPiston(1.0));
	}
	
	public Joystick getPrimaryJoystick(){
		return PrimaryJoystick;
	}
}