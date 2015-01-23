package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class EncodedTurn extends Command {
	private boolean isDone;
	private boolean isFirstTime;
	private double encoderValue;
	private double rotations;

    public EncodedTurn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.winch);    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isFirstTime = true;
    	isDone = false;
    	rotations = SmartDashboard.getNumber("Winch Rotations: ", 1);
    	encoderValue = 128 * rotations + Robot.winch.getEncoderDistance();
    	System.out.println("Encoder Limit: " + encoderValue);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(rotations > 0){
    		if(Robot.winch.getEncoderDistance() < encoderValue && isFirstTime){
    			Robot.winch.setMotorSpeed(.5);
    			isFirstTime = false;
    		}
    		if(Robot.winch.getEncoderDistance() >= encoderValue)
    			isDone = true;
    	}else{
    		if(Robot.winch.getEncoderDistance() > encoderValue && isFirstTime){
    			Robot.winch.setMotorSpeed(-.5);
    			isFirstTime = false;
    		}
    		if(Robot.winch.getEncoderDistance() <= encoderValue)
    			isDone = true;
    	}
    	Robot.winch.logEncoderData();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.winch.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.winch.stopMotor();
    }
}
