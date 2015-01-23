package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GetRearDistance extends Command {

	private boolean isDone;
	
    public GetRearDistance() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rangefinder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isDone = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	double currDistance = Robot.rangefinder.getDistance(RobotMap.RANGEFINDER.REAR_RANGEFINDER);
    	System.out.println("Rear RangeFinder Distance: " + currDistance);
    	SmartDashboard.putNumber("Rear RangeFinder Distance: ", currDistance);
    	isDone = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
