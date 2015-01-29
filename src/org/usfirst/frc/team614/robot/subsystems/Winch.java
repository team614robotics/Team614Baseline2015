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
	private Talon WinchTalon;
	private Encoder WinchEncoder;
	
	private double MOTOR_SPEED = 0.5;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new AssignWinchSpeed());
    }
    
    public Winch(){
    	
    	WinchTalon = new Talon(RobotMap.WINCH_MOTOR);
    	//WinchEncoder = new Encoder(RobotMap.WINCH_ENCODER_A, RobotMap.WINCH_ENCODER_B, true, EncodingType.k4X);
    }
    
    public void startMotor(){
    	WinchTalon.set(MOTOR_SPEED);
    }
    
    public void stopMotor(){
    	WinchTalon.set(0);
    }
    
    public void reverseMotor(){
    	WinchTalon.set(-MOTOR_SPEED);
    }
    
    public void setMotorSpeed(double Speed){
    	WinchTalon.set(Speed);
    }
    
    public double getEncoderDistance(){
    	return WinchEncoder.getDistance();
    }
    
    public boolean getEncoderDirection(){
    	return WinchEncoder.getDirection();
    }
    
    public void logEncoderData(){
    	SmartDashboard.putNumber("Winch Encoder Distance: ", Robot.winch.getEncoderDistance());
    	SmartDashboard.putString("Winch Encoder Direction: ", (Robot.winch.getEncoderDirection() ? "Clockwise" : "Counter-Clockwise"));
    }
    
    public void resetEncoder(){
    	WinchEncoder.reset();
    }
    
}