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

/**
 *
 */
public class Chassis extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Jaguar LeftFrontMotor, LeftRearMotor, RightFrontMotor, RightRearMotor;
	private Encoder LeftFrontEncoder, LeftRearEncoder, RightFrontEncoder, RightRearEncoder;
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
    	
    	LeftFrontEncoder = new Encoder(RobotMap.LEFT_FRONT_ENCODER_A, RobotMap.LEFT_FRONT_ENCODER_B, true, EncodingType.k4X);
    	LeftRearEncoder = new Encoder(RobotMap.LEFT_REAR_ENCODER_A, RobotMap.LEFT_REAR_ENCODER_B, true, EncodingType.k4X);
    	RightFrontEncoder = new Encoder(RobotMap.RIGHT_FRONT_ENCODER_A, RobotMap.RIGHT_FRONT_ENCODER_B, true, EncodingType.k4X);
    	RightRearEncoder = new Encoder(RobotMap.RIGHT_REAR_ENCODER_A, RobotMap.RIGHT_REAR_ENCODER_B, true, EncodingType.k4X);
    	
    	Drive = new RobotDrive(LeftFrontMotor, LeftRearMotor, RightFrontMotor, RightRearMotor);   	
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
    
    public void mecanumDriveMode(Joystick Controller){
    	
    	Drive.mecanumDrive_Polar(Math.abs(Controller.getRawAxis(1)), Controller.getDirectionDegrees(), Controller.getRawAxis(4));
    }
    
    //EDIT FOR USE IN ALL DRIVE MODES
    public void manualDrive(double Magnitude, double Degrees, double Rotation){
    	Drive.mecanumDrive_Polar(Magnitude, Degrees, Rotation);
    }
    
    public void straight(){
    	Drive.arcadeDrive(1.0, 1.0);
    }
    
    
    //ENCODER STUFF
    public double getEncoderDistance(Encoder encoder){
    	return encoder.getDistance();
    }
    
    
    //Is this necessary?
    public boolean getEncoderDirection(encoder){
    	return encoder.getDirection();
    }
    
    public void logEncoderData(){
    	SmartDashboard.putNumber("Left Front Encoder Distance: ", Robot.chassis.getEncoderDistance(LeftFrontEncoder));
    	//SmartDashboard.putString("Left Front Encoder Direction: ", (Robot.chassis.getEncoderDirection(LeftFrontEncoder) ? "Clockwise" : "Counter-Clockwise"));
    	
    	SmartDashboard.putNumber("Left Rear Encoder Distance: ", Robot.chassis.getEncoderDistance(LeftRearEncoder));
    	//SmartDashboard.putString("Left Rear Encoder Direction: ", (Robot.chassis.getEncoderDirection(LeftRearEncoder) ? "Clockwise" : "Counter-Clockwise"));
    
    	SmartDashboard.putNumber("Right Front Encoder Distance: ", Robot.chassis.getEncoderDistance(RightFrontEncoder));
    	//SmartDashboard.putString("Right Front Encoder Direction: ", (Robot.chassis.getEncoderDirection(RightFrontEncoder) ? "Clockwise" : "Counter-Clockwise"));
      	
    	SmartDashboard.putNumber("Right Rear Encoder Distance: ", Robot.chassis.getEncoderDistance(RightRearEncoder));
    	//SmartDashboard.putString("Right Rear Encoder Direction: ", (Robot.chassis.getEncoderDirection(RightRearEncoder) ? "Clockwise" : "Counter-Clockwise"));
    
    }
    
    public void resetEncoder(){
    	LeftFrontEncoder.reset();
    	LeftRearEncoder.reset();
    	RightFrontEncoder.reset();
    	RightRearEncoder.reset();
    	
    }
}

