package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerWinch extends Command {

	public static boolean firstTime;
	public static double timeout;
	
    public LowerWinch(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.winch);
    	timeout=time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	firstTime = true;
    	setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	if(firstTime){
    		Robot.winch.reverseMotor();
    		firstTime = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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