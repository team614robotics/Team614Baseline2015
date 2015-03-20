package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveWinch extends Command {

	private double WinchDesiredValue;
	private boolean MoveDirection; //(TRUE - UP, FALSE - DOWN)
	private double MotorSpeed;
	private double prevEncValue;
	private double EncoderChange;
	
    public MoveWinch(double Distance, boolean Direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.winch);
    	
    	if(Direction){
    		WinchDesiredValue = Robot.winch.getEncoderDistance() + Distance;
    	}else{
    		WinchDesiredValue = Robot.winch.getEncoderDistance() - Distance;
    	}
    	
    	MoveDirection = Direction;
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    	MotorSpeed = 0.0;
    	prevEncValue = Robot.winch.getEncoderDistance();
    	EncoderChange = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	if(Robot.winch.getEncoderDistance() < WinchDesiredValue){
    		EncoderChange = Robot.winch.getEncoderDistance() - prevEncValue;
    		if(EncoderChange < RobotMap.ENCODER_RANGE){
    			MotorSpeed = (MotorSpeed + 0.05 > 1.0 ? 1.0 : MotorSpeed + 0.05); 
    					/*
    					 * Modified if-else statement. If MotorSpeed is above .95, then set it to 1. 
    					 * 								If not, increase the MotorSpeed by increments of 0.05
    					 */
    		}
    		Robot.winch.setMotorSpeed(MotorSpeed);
    	}else{
    		MotorSpeed = 0.0;
    		EncoderChange = Robot.winch.getEncoderDistance() - prevEncValue;
    		if(Math.abs(EncoderChange) > RobotMap.ENCODER_RANGE){ //If the winch changed by more than 2 ticks, 
    			MotorSpeed = (MotorSpeed + 0.05 > 1.0 ? 1.0 : MotorSpeed + 0.05);
    					/*
    					 * Modified if-else statement. If MotorSpeed is above .95, then set it to 1. 
    					 * 								If not, increase the MotorSpeed by increments of 0.05
    					 */
    			Robot.winch.setMotorSpeed(MotorSpeed);
    		}else{
    			/**
    			 * Either set the motors to a stall speed, and risk
    			 * everything ******* up when there is no weight on
    			 * the winch, or set it to zero and have to continually
    			 * rev-up the motors.
    			 */
    			
    			Robot.winch.stopMotor();
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end(){
    	Robot.winch.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.winch.stopMotor();
    }
}
