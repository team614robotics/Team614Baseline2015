package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CassiesRAVE_COMMAND extends Command {

	
	double timeout = 0.0;
    public CassiesRAVE_COMMAND(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.camera);
    	timeout = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeout);
    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double HorizontalValue = Math.random();
    	double VerticalValue = Math.random();
    	
    	Robot.camera.setServoPosition(VerticalValue, HorizontalValue);
    	Robot.camera.logServoPosition();
    	try{
    		this.wait(300);
    	}catch(InterruptedException IE){
    		System.out.println("Could not accomplish designated wait time.");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.camera.setServoPosition(0.5, 0.5);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.camera.setServoPosition(0.5, 0.5);
    }
}
