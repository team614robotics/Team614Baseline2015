package org.usfirst.frc.team614.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//CHASSIS CONSTANTS
	public enum DriveMode{
		
		TANK_DRIVE,
		ARCADE_DRIVE,
		MECANUM_DRIVE
	}
	
	public static DriveMode DRIVE_MODE = DriveMode.MECANUM_DRIVE;
	public static final int LEFT_FRONT_MOTOR = 2;
	public static final int LEFT_REAR_MOTOR = 0;
	public static final int RIGHT_FRONT_MOTOR = 4;
	public static final int RIGHT_REAR_MOTOR = 5;
	
	
	//CHANGE THE VALUES OF THESE ENCODER
	public static final int LEFT_FRONT_ENCODER_A = 0;
	public static final int LEFT_FRONT_ENCODER_B = 1;
	public static final int LEFT_REAR_ENCODER_A = 0;
	public static final int LEFT_REAR_ENCODER_B = 1;
	public static final int RIGHT_FRONT_ENCODER_A = 0;
	public static final int RIGHT_FRONT_ENCODER_B = 1;
	public static final int RIGHT_REAR_ENCODER_A = 0;
	public static final int RIGHT_REAR_ENCODER_B = 1;
	
	//RANGEFINDER CONSTANTS
	public static enum RANGEFINDER{
		FRONT_RANGEFINDER,
		REAR_RANGEFINDER,
		LEFT_RANGEFINDER,
		RIGHT_RANGEFINDER
	}
	
	public static final int FRONT_RANGEFINDER_AC = 0;
	public static final int REAR_RANGEFINDER_AC = 1;
	public static final int LEFT_RANGEFINDER_AC = 2;
	public static final int RIGHT_RANGEFINDER_AC = 3;
	
	//GYROSCOPE CONSTANTS
	public static final int GYROSCOPE_AC = 1;
	
	//WINCH CONSTANTS
	public static final int WINCH_MOTOR = 1;
	public static final int WINCH_ENCODER_A = 0;
	public static final int WINCH_ENCODER_B = 1;
	
	//JOYSTICK CONSTANTS
	public static final int PRIMARY_JOYSTICK = 0;
	public static final double JOYSTICK_DEADBAND = 0.2;
	
}
