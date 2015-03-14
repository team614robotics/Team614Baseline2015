package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.Drive_Joystick;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.VictorSP;

/**
 *
 */
public class Chassis extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final double SPEED_SCALE = 1.0;
	
	//private Jaguar LeftFrontMotor, LeftRearMotor, RightFrontMotor, RightRearMotor;
	private VictorSP LeftFrontMotor, LeftRearMotor, RightFrontMotor, RightRearMotor;
	private Encoder LeftFrontEncoder, LeftRearEncoder, RightFrontEncoder, RightRearEncoder;
	
	public static final int LFEncoder = 0;
	public static final int RFEncoder = 1;
	public static final int LREncoder = 2;
	public static final int RREncoder = 3;
	
	private RobotDrive Drive;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive_Joystick());
    }
    
    public Chassis(){
    	
    	/**
    	 LeftFrontMotor = new Jaguar(RobotMap.LEFT_FRONT_MOTOR);
     	 LeftRearMotor = new Jaguar(RobotMap.LEFT_REAR_MOTOR);
    	 RightFrontMotor = new Jaguar(RobotMap.RIGHT_FRONT_MOTOR);
    	 RightRearMotor = new Jaguar(RobotMap.RIGHT_REAR_MOTOR);
    	 */
    	
    	 LeftFrontMotor = new VictorSP(RobotMap.LEFT_FRONT_MOTOR);
    	 LeftRearMotor = new VictorSP(RobotMap.LEFT_REAR_MOTOR);
    	 RightFrontMotor = new VictorSP(RobotMap.RIGHT_FRONT_MOTOR);
    	 RightRearMotor = new VictorSP(RobotMap.RIGHT_REAR_MOTOR);
    	
    	LeftFrontEncoder = new Encoder(RobotMap.LEFT_FRONT_ENCODER_A, RobotMap.LEFT_FRONT_ENCODER_B, true, EncodingType.k4X);
    	LeftRearEncoder = new Encoder(RobotMap.LEFT_REAR_ENCODER_A, RobotMap.LEFT_REAR_ENCODER_B, true, EncodingType.k4X);
    	RightFrontEncoder = new Encoder(RobotMap.RIGHT_FRONT_ENCODER_A, RobotMap.RIGHT_FRONT_ENCODER_B, true, EncodingType.k4X);
    	RightRearEncoder = new Encoder(RobotMap.RIGHT_REAR_ENCODER_A, RobotMap.RIGHT_REAR_ENCODER_B, true, EncodingType.k4X);
    	
    	Drive = new RobotDrive(LeftFrontMotor, LeftRearMotor, RightFrontMotor, RightRearMotor);
    	Drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true); //these two motors are inverted because the motors are pointed in opposite directions
    	Drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
    }
    
    public void tankDriveMode(Joystick Controller){
    	double leftValue = 0, rightValue = 0;
    	leftValue = Controller.getRawAxis(0); 
    	rightValue = Controller.getRawAxis(4);
    	
    	// Simplified IF statement. If leftValue is in the deadband range(-JoystickDeadband, JoystickDeadband), it returns 0
    	leftValue = (leftValue < RobotMap.JOYSTICK_DEADBAND && leftValue > -RobotMap.JOYSTICK_DEADBAND ? 0 : leftValue);
    	
    	// Simplified IF statement. If rightValue is in the deadband range(-JoystickDeadband, JoystickDeadband), it returns 0
    	rightValue = (rightValue < RobotMap.JOYSTICK_DEADBAND && rightValue > -RobotMap.JOYSTICK_DEADBAND ? 0 : rightValue);
    	
    	//System.out.println("Tank Drive: " + leftValue + ", " + rightValue);
    	
    	Drive.tankDrive(leftValue, rightValue);
    }
    
    public void arcadeDriveMode(Joystick Controller){
    	double leftValue = 0, rightValue = 0;
    	leftValue = Controller.getRawAxis(0);
    	rightValue = Controller.getRawAxis(4);
    	
    	// Simplified IF statement. If leftValue is in the deadband range(-JoystickDeadband, JoystickDeadband), it returns 0
    	leftValue = (leftValue < RobotMap.JOYSTICK_DEADBAND && leftValue > -RobotMap.JOYSTICK_DEADBAND ? 0 : leftValue);
    	
    	// Simplified IF statement. If rightValue is in the deadband range(-JoystickDeadband, JoystickDeadband), it returns 0
    	rightValue = (rightValue < RobotMap.JOYSTICK_DEADBAND && rightValue > -RobotMap.JOYSTICK_DEADBAND ? 0 : rightValue);
    	
    	//System.out.println("Tank Drive: " + leftValue + ", " + rightValue);
    	
    	Drive.arcadeDrive(leftValue, rightValue);
    }
    
    /* Jered - 1/24/2015 - Test Assisted Driving for Viability
     *                     May need to change Degrees values */
    public void mecanumDriveMode(Joystick Controller){
    	
    	//ADD RAMP FUNCTION; Remove SPEED_SCALE once this is implemented
    	 	//See declarations for Magnitude and Degrees, and Rotation
    		//Default Ramp Function = 100
    		//Current Ramp Function = (x^2)/100
    		//			also try    = (x^3)/10000
    	//ADD RAMP FUNCTION
    	//double Magnitude = -(Controller.getMagnitude()); // getMagnitude() returns a value between 0 and 1
    	//double Magnitude = -(((Controller.getMagnitude()*10) * (Controller.getMagnitude()*10)))/100;
    	double ControllerValue = Controller.getMagnitude();
    	ControllerValue = ((ControllerValue < RobotMap.JOYSTICK_DEADBAND && ControllerValue > -RobotMap.JOYSTICK_DEADBAND) ? 0 : ControllerValue); // if magnitude is within the deadband range, set it to 0. If not, don't modify it. 
    	double Magnitude = -(1.0039215686275 * Math.pow(ControllerValue, 3) - 0.00616246498603 * ControllerValue);
    	
    	double Degrees = 0.0;
    	double Rotation = 0.0;
    	
    	SmartDashboard.putNumber("Degrees: ", Controller.getDirectionDegrees());
    	Degrees = Controller.getDirectionDegrees();
    	
    	Rotation = -(Controller.getRawAxis(4));
    	//Rotation = -(Controller.getRawAxis(4) * Controller.getRawAxis(4))/100; //Axis 4  =  right analog stick = rotation
    	Rotation = ((Rotation < RobotMap.JOYSTICK_DEADBAND && Rotation > -RobotMap.JOYSTICK_DEADBAND) ? 0 : Rotation); //if rotation is within the deadband range, set it equal to 0. If not, don't modify it. 
    	
    	Drive.mecanumDrive_Polar(Magnitude / SPEED_SCALE, Degrees, Rotation / SPEED_SCALE); //To increase the sensitivity, decrease SPEED_SCALE to a number below one but above 0
    																						//To decrease the sensitivity, increase SPEED_SCALE to a number above one; 		SPEED_SCALE is here until a proper ramp function is implemented
    }
    
    //EDIT FOR USE IN ALL DRIVE MODES
    public void manualDrive(double Magnitude, double Degrees, double Rotation){
    	Magnitude = ((Magnitude < RobotMap.JOYSTICK_DEADBAND && Magnitude > -RobotMap.JOYSTICK_DEADBAND) ? 0 : Magnitude);
    	
    	Drive.mecanumDrive_Polar(Magnitude / SPEED_SCALE, Degrees, Rotation / SPEED_SCALE);
    }
    
    public void motorSpeedDrive(double LeftFrontMotorSpeed, double LeftRearMotorSpeed,
    		                    double RightFrontMotorSpeed, double RightRearMotorSpeed){
    	LeftFrontMotor.set(LeftFrontMotorSpeed);
    	LeftRearMotor.set(LeftRearMotorSpeed);
    	RightFrontMotor.set(RightFrontMotorSpeed);
    	RightRearMotor.set(RightRearMotorSpeed);
    }
    
    public void straight(){
    	Drive.arcadeDrive(1.0, 1.0);
    	
    }
    
    public void stopChassis(){
    	Drive.stopMotor();
    }
    
    public double getEncoderDistance(int encoderNum){ 
    	switch(encoderNum){
    		case LFEncoder: //if = 0
    			return LeftFrontEncoder.getDistance();
    		case RFEncoder: //if = 1
    			return RightFrontEncoder.getDistance();
    		case LREncoder: //if = 2
    			return LeftRearEncoder.getDistance();
    		case RREncoder: //if = 3
    			return RightRearEncoder.getDistance();
    		default:
    			return 0.0;
    	}
    }
    
   
    public boolean getEncoderDirection(int encoderNum){
    	switch(encoderNum){
			case LFEncoder: //if = 0
				return LeftFrontEncoder.getDirection();
			case RFEncoder: //if = 1
				return RightFrontEncoder.getDirection();
			case LREncoder: //if = 2
				return LeftRearEncoder.getDirection();
			case RREncoder: //if = 3
				return RightRearEncoder.getDirection();
			default:
				return false;
		}
    }
    
    /* For Logging the Encoder Values to the SmartDashboard */
    public void logEncoderData(){
    	SmartDashboard.putNumber("Left Front Encoder Distance: ", Robot.chassis.getEncoderDistance(LFEncoder));
    	SmartDashboard.putString("Left Front Encoder Direction: ", (Robot.chassis.getEncoderDirection(LFEncoder) ? "Clockwise" : "Counter-Clockwise"));
    	
    	SmartDashboard.putNumber("Left Rear Encoder Distance: ", Robot.chassis.getEncoderDistance(LREncoder));
    	SmartDashboard.putString("Left Rear Encoder Direction: ", (Robot.chassis.getEncoderDirection(LREncoder) ? "Clockwise" : "Counter-Clockwise"));
    
    	SmartDashboard.putNumber("Right Front Encoder Distance: ", Robot.chassis.getEncoderDistance(RFEncoder));
    	SmartDashboard.putString("Right Front Encoder Direction: ", (Robot.chassis.getEncoderDirection(RFEncoder) ? "Clockwise" : "Counter-Clockwise"));
      	
    	SmartDashboard.putNumber("Right Rear Encoder Distance: ", Robot.chassis.getEncoderDistance(RREncoder));
    	SmartDashboard.putString("Right Rear Encoder Direction: ", (Robot.chassis.getEncoderDirection(RREncoder) ? "Clockwise" : "Counter-Clockwise"));
    }
    
    public void resetEncoder(){
    	LeftFrontEncoder.reset();
    	LeftRearEncoder.reset();
    	RightFrontEncoder.reset();
    	RightRearEncoder.reset();
    	
    }
}

