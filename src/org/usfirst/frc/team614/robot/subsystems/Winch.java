package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;
import org.usfirst.frc.team614.robot.commands.AssignWinchSpeed;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Winch extends Subsystem{
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Talon PrimaryWinchTalon;
	private Talon SecondaryWinchTalon;
	private Encoder WinchEncoder;
	
	private double MOTOR_SPEED = 0.5;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new AssignWinchSpeed());
    }
    
    public Winch(){
    	
    	PrimaryWinchTalon = new Talon(RobotMap.PRIMARY_WINCH_MOTOR);
    	SecondaryWinchTalon = new Talon(RobotMap.SECONDARY_WINCH_MOTOR);
    	//WinchEncoder = new Encoder(RobotMap.WINCH_ENCODER_A, RobotMap.WINCH_ENCODER_B, true, EncodingType.k4X);
    }
    
    public void startMotor(){
    	PrimaryWinchTalon.set(MOTOR_SPEED);
    	SecondaryWinchTalon.set(MOTOR_SPEED);
    }
    
    public void stopMotor(){
    	PrimaryWinchTalon.set(0);
    	SecondaryWinchTalon.set(0);
    }
    
    public void reverseMotor(){
    	PrimaryWinchTalon.set(-MOTOR_SPEED);
    	SecondaryWinchTalon.set(-MOTOR_SPEED);
    }
    
    public void setMotorSpeed(double Speed){
    	PrimaryWinchTalon.set(Speed);
    	SecondaryWinchTalon.set(Speed);
    }
    
    public double getEncoderDistance(){
    	return WinchEncoder.getDistance();
    }
    
    /*public boolean getEncoderDirection(){
    	return WinchEncoder.getDirection();
    }
    
    public void logEncoderData(){
    	SmartDashboard.putNumber("Winch Encoder Distance: ", Robot.winch.getEncoderDistance());
    	SmartDashboard.putString("Winch Encoder Direction: ", (Robot.winch.getEncoderDirection() ? "Clockwise" : "Counter-Clockwise"));
    	
    	
    }
    
    public void resetEncoder(){
    	WinchEncoder.reset();
    }*/
    
}