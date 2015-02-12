package org.usfirst.frc.team614.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team614.robot.commands.AssignWinchSpeed;
import org.usfirst.frc.team614.robot.commands.CassiesRAVE_COMMAND;
import org.usfirst.frc.team614.robot.commands.ClearAssisted;
import org.usfirst.frc.team614.robot.commands.CloseArms;
import org.usfirst.frc.team614.robot.commands.CycleWinch_CG;
import org.usfirst.frc.team614.robot.commands.DriveStraight_CG;
import org.usfirst.frc.team614.robot.commands.ExampleCommand;
import org.usfirst.frc.team614.robot.commands.ChangeDriveMode;
import org.usfirst.frc.team614.robot.commands.OpenArms;
import org.usfirst.frc.team614.robot.commands.RAVE_PARTY;
import org.usfirst.frc.team614.robot.commands.ResetWinchEncoder;
import org.usfirst.frc.team614.robot.commands.StraightenRobot;
import org.usfirst.frc.team614.robot.commands.DriveStraight;
import org.usfirst.frc.team614.robot.commands.EncodedTurn;
import org.usfirst.frc.team614.robot.commands.ToggleAssistedBackward;
import org.usfirst.frc.team614.robot.commands.ToggleAssistedForward;
import org.usfirst.frc.team614.robot.commands.ToggleAssistedLeft;
import org.usfirst.frc.team614.robot.commands.ToggleAssistedRight;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick PrimaryJoystick = new Joystick(RobotMap.PRIMARY_JOYSTICK);
	
	private JoystickButton DriveModeButton;
	private JoystickButton StraightenButton;
	private JoystickButton DriveStraight;
	private JoystickButton CycleWinchButton;
	private JoystickButton ResetWinchEncoderButton;
	private JoystickButton EncodedTurn;
	
	private JoystickButton ToggleAForwardButton;
	private JoystickButton ToggleABackwardButton;
	private JoystickButton ToggleALeftButton;
	private JoystickButton ToggleARightButton;
	private JoystickButton ClearAssistedButton;
	
	private JoystickButton OpenArmsButton;
	private JoystickButton CloseArmsButton;
	
	//private JoystickButton RAVEBUTTON;
	
	public OI(){
		
		//DriveModeButton = new JoystickButton(PrimaryJoystick, 8);
		//DriveModeButton.toggleWhenActive(new ChangeDriveMode());
		
		//StraightenButton = new JoystickButton(PrimaryJoystick, 4);
		//StraightenButton.toggleWhenActive(new StraightenRobot());
		
		//DriveStraight = new JoystickButton(PrimaryJoystick, 1);
		//DriveStraight.toggleWhenPressed(new DriveStraight());
		
		//DriveStraight = new JoystickButton(PrimaryJoystick, 2);
		//DriveStraight.toggleWhenActive(new DriveStraight_CG());
		
		//CycleWinchButton = new JoystickButton(PrimaryJoystick, 3);
		//CycleWinchButton.toggleWhenActive(new CycleWinch_CG());
		
		//ResetWinchEncoderButton = new JoystickButton(PrimaryJoystick, 2);
		//ResetWinchEncoderButton.toggleWhenActive(new ResetWinchEncoder());
		
		//EncodedTurn = new JoystickButton(PrimaryJoystick, 4);
		//EncodedTurn.toggleWhenActive(new EncodedTurn());
		
		ToggleAForwardButton = new JoystickButton(PrimaryJoystick, 4);
		ToggleAForwardButton.toggleWhenActive(new ToggleAssistedForward());
		
		ToggleALeftButton = new JoystickButton(PrimaryJoystick, 3);
		ToggleALeftButton.toggleWhenActive(new ToggleAssistedLeft());
		
		ToggleARightButton = new JoystickButton(PrimaryJoystick, 2);
		ToggleARightButton.toggleWhenActive(new ToggleAssistedRight());
		
		ToggleABackwardButton = new JoystickButton(PrimaryJoystick, 1);
		ToggleABackwardButton.toggleWhenActive(new ToggleAssistedBackward());
		
		ClearAssistedButton = new JoystickButton(PrimaryJoystick, 7);
		ClearAssistedButton.toggleWhenActive(new ClearAssisted());
		
		OpenArmsButton = new JoystickButton(PrimaryJoystick, 5);
		OpenArmsButton.toggleWhenActive(new OpenArms(1.0));
		
		CloseArmsButton = new JoystickButton(PrimaryJoystick, 6);
		CloseArmsButton.toggleWhenActive(new CloseArms(1.0));
		
		//RAVEBUTTON = new JoystickButton(PrimaryJoystick, 5);
		//RAVEBUTTON.toggleWhenPressed(new RAVE_PARTY(5));
	}
	
	public Joystick getPrimaryJoystick(){
		return PrimaryJoystick;
	}
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
}

