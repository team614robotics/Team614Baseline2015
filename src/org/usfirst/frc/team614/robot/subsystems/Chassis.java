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
	
	private final double SPEED_SCALE = 4.0;
	
	private Jaguar LeftFrontMotor, LeftRearMotor, RightFrontMotor, RightRearMotor;
	//private VictorSP LeftFrontMotor, LeftRearMotor, RightFrontMotor, RightRearMotor;
	private Encoder LeftFrontEncoder, LeftRearEncoder, RightFrontEncoder, RightRearEncoder;
	
	public static final int LFEncoder = 0;
	public static final int RFEncoder = 1;
	public static final int LREncoder = 2;
	public static final int RREncoder = 3;
	
	private boolean ASSISTED_FORWARD = false;
	private boolean ASSISTED_LEFT = false;
	private boolean ASSISTED_RIGHT = false;
	private boolean ASSISTED_BACKWARD = false;
	
	private RobotDrive Drive;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive_Joystick());
    }
    
    public Chassis(){
    	
    	
    	 LeftFrontMotor = new Jaguar(RobotMap.LEFT_FRONT_MOTOR);
     	 LeftRearMotor = new Jaguar(RobotMap.LEFT_REAR_MOTOR);
    	 RightFrontMotor = new Jaguar(RobotMap.RIGHT_FRONT_MOTOR);
    	 RightRearMotor = new Jaguar(RobotMap.RIGHT_REAR_MOTOR);
    	 
    	
    	/**
    	 LeftFrontMotor = new VictorSP(RobotMap.LEFT_FRONT_MOTOR);
    	 LeftRearMotor = new VictorSP(RobotMap.LEFT_REAR_MOTOR);
    	 RightFrontMotor = new VictorSP(RobotMap.RIGHT_FRONT_MOTOR);
    	 RightRearMotor = new VictorSP(RobotMap.RIGHT_REAR_MOTOR);
    	 */
    	
    	LeftFrontEncoder = new Encoder(RobotMap.LEFT_FRONT_ENCODER_A, RobotMap.LEFT_FRONT_ENCODER_B, true, EncodingType.k4X);
    	LeftRearEncoder = new Encoder(RobotMap.LEFT_REAR_ENCODER_A, RobotMap.LEFT_REAR_ENCODER_B, true, EncodingType.k4X);
    	RightFrontEncoder = new Encoder(RobotMap.RIGHT_FRONT_ENCODER_A, RobotMap.RIGHT_FRONT_ENCODER_B, true, EncodingType.k4X);
    	RightRearEncoder = new Encoder(RobotMap.RIGHT_REAR_ENCODER_A, RobotMap.RIGHT_REAR_ENCODER_B, true, EncodingType.k4X);
    	
    	Drive = new RobotDrive(LeftFrontMotor, LeftRearMotor, RightFrontMotor, RightRearMotor);
    	Drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
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
    	
    	double Magnitude = -Controller.getMagnitude();
    	Magnitude = ((Magnitude < RobotMap.JOYSTICK_DEADBAND && Magnitude > -RobotMap.JOYSTICK_DEADBAND) ? 0 : Magnitude);
    	
    	double Degrees = 0.0;
    	double Rotation = 0.0;
    	
    	if(ASSISTED_FORWARD){
    		Magnitude = Math.abs(Magnitude);
    		Degrees = 180.0;
    	}else if(ASSISTED_LEFT){
    		Magnitude = Math.abs(Magnitude);
    		Degrees = 90.0;
    	}else if(ASSISTED_RIGHT){
    		Magnitude = Math.abs(Magnitude);
    		Degrees = 270.0;
    	}else if(ASSISTED_BACKWARD){
    		Magnitude = Math.abs(Magnitude);
    		Degrees = 0.0;
    	}else{
    		Degrees = Controller.getDirectionDegrees();
    	}
    	
    	Rotation = -Controller.getRawAxis(4);
    	Rotation = ((Rotation < RobotMap.JOYSTICK_DEADBAND && Rotation > -RobotMap.JOYSTICK_DEADBAND) ? 0 : Rotation);
    	
    	Drive.mecanumDrive_Polar(Magnitude / SPEED_SCALE, Degrees, Rotation / SPEED_SCALE);
    }
    
    //EDIT FOR USE IN ALL DRIVE MODES
    public void manualDrive(double Magnitude, double Degrees, double Rotation){
    	Magnitude = ((Magnitude < RobotMap.JOYSTICK_DEADBAND && Magnitude > -RobotMap.JOYSTICK_DEADBAND) ? 0 : Magnitude);
    	
    	if(ASSISTED_FORWARD){
    		Magnitude = Math.abs(Magnitude);
    		Degrees = 180.0;
    	}else if(ASSISTED_LEFT){
    		Magnitude = Math.abs(Magnitude);
    		Degrees = 90.0;
    	}else if(ASSISTED_RIGHT){
    		Magnitude = Math.abs(Magnitude);
    		Degrees = 270.0;
    	}else if(ASSISTED_BACKWARD){
    		Magnitude = Math.abs(Magnitude);
    		Degrees = 0.0;
    	}
    	
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
    		case LFEncoder:
    			return LeftFrontEncoder.getDistance();
    		case RFEncoder:
    			return RightFrontEncoder.getDistance();
    		case LREncoder:
    			return LeftRearEncoder.getDistance();
    		case RREncoder:
    			return RightRearEncoder.getDistance();
    		default:
    			return 0.0;
    	}
    }
    
    
    public boolean getEncoderDirection(int encoderNum){
    	switch(encoderNum){
			case LFEncoder:
				return LeftFrontEncoder.getDirection();
			case RFEncoder:
				return RightFrontEncoder.getDirection();
			case LREncoder:
				return LeftRearEncoder.getDirection();
			case RREncoder:
				return RightRearEncoder.getDirection();
			default:
				return false;
		}
    }
    
    public void logData(){
    	logAssistedData();
    	logEncoderData();
    }
    
    /* For Logging the Assisted Driving Booleans to the SmartDashboard */
    public void logAssistedData(){
    	SmartDashboard.putBoolean("Assisted Forward: ",  ASSISTED_FORWARD);
    	SmartDashboard.putBoolean("Assisted Backward: ", ASSISTED_BACKWARD);
    	SmartDashboard.putBoolean("Assisted Left: ",     ASSISTED_LEFT);
    	SmartDashboard.putBoolean("Assisted Right: ",    ASSISTED_RIGHT);
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
    
    public void toggleAssistedForward(){
    	ASSISTED_FORWARD = !ASSISTED_FORWARD;
    }
    
    public void toggleAssistedLeft(){
    	ASSISTED_LEFT = !ASSISTED_LEFT;
    }
    
    public void toggleAssistedRight(){
    	ASSISTED_RIGHT = !ASSISTED_RIGHT;
    }
    
    public void toggleAssistedBackward(){
    	ASSISTED_BACKWARD = !ASSISTED_BACKWARD;
    }
    
    public void clearAssisted(){
    	ASSISTED_FORWARD = false;
    	ASSISTED_LEFT = false;
    	ASSISTED_RIGHT = false;
    	ASSISTED_BACKWARD = false;
    }
}

