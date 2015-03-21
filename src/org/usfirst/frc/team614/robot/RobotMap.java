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
	
	//AUTONOMOUS MODE CONSTANTS
	public static final double AUTO_MOTOR_MAGNITUDE = 1.0;
	public static final double AUTO_ANGLE_RANGE = 2.0;
	
	//CAMERA CONSTANTS
	public static final String CAMERA_IP = "10.6.14.20";
	public static final int HORIZONTAL_SERVO_PWM = 9;
	public static final int VERTICAL_SERVO_PWM = 8;
	
	//CHASSIS CONSTANTS
	public static final int LEFT_FRONT_MOTOR = 3;//0;//2;
	public static final int LEFT_REAR_MOTOR = 0;//2;//0;  //switched this from RIGHT_REAR_MOTOR to test switching rotate and strafe functions
	public static final int RIGHT_FRONT_MOTOR = 5;//1;//4;
	public static final int RIGHT_REAR_MOTOR = 4;//3;//5  //switched this from LEFT_REAR_MOTOR to test switching rotate and strafe functions
	
	
	//CHANGE THE VALUES OF THESE ENCODER
	public static final int LEFT_FRONT_ENCODER_A = 7;//0
	public static final int LEFT_FRONT_ENCODER_B = 6;//1
	public static final int LEFT_REAR_ENCODER_A = 3;//2
	public static final int LEFT_REAR_ENCODER_B = 2;//3
	public static final int RIGHT_FRONT_ENCODER_A = 4;//4
	public static final int RIGHT_FRONT_ENCODER_B = 5;//5
	public static final int RIGHT_REAR_ENCODER_A = 8;//6
	public static final int RIGHT_REAR_ENCODER_B = 9;//7
	
	//RANGEFINDER CONSTANTS
	public static enum RANGEFINDER{
		FRONT_RANGEFINDER,
		REAR_RANGEFINDER
	}
	
	public static final int FRONT_RANGEFINDER_AC = 0;
	public static final int REAR_RANGEFINDER_AC = 2; // Comment these out if only the Front Rangefinder is in use (REAR_RANGEFINDER, LEFT_RANGEFINDER, RIGHT_RANGFINDERLEFT_RANGEFINDER)
	
	//GYROSCOPE CONSTANTS
	public static final int GYROSCOPE_AC = 1;
	
	//ACCELEROMETER CONSTANTS
	public static final double ACCELEROMETER_RANGE = 0.5;
	
	//PNEUMATIC CONSTANTS
	public static final int PCM_ID = 0;
	public static final int PISTON_ID = 5;
	
	//WINCH CONSTANTS
	public static final int PRIMARY_WINCH_MOTOR = 1;//5;//1;//5;
	public static final int SECONDARY_WINCH_MOTOR = 2;//4;//3;//4;
	public static final double WINCH_STALL_SPEED = 0.3; //NEED TO TEST
	public static final int WINCH_ENCODER_A = 0;
	public static final int WINCH_ENCODER_B = 1;
	public static final int ENCODER_RANGE = 2;
	public static final double WINCH_LOW_DISTANCE = 0;
	public static final double WINCH_MEDIUM_DISTANCE = 5;
	public static final double WINCH_HIGH_DISTANCE = 10;
	
	//JOYSTICK CONSTANTS
	public static final int PRIMARY_JOYSTICK = 0;
	public static final double JOYSTICK_DEADBAND = 0.1;
	
}
