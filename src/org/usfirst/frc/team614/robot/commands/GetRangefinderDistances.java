package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap.RANGEFINDER;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GetRangefinderDistances extends Command {
	
    public GetRangefinderDistances(){
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rangefinder);
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	double currDistance = Robot.rangefinder.getDistance(RANGEFINDER.FRONT_RANGEFINDER);
    	SmartDashboard.putNumber("Front RangeFinder Distance", currDistance);
    	//currDistance = Robot.rangefinder.getDistance(RANGEFINDER.REAR_RANGEFINDER);
    	//SmartDashboard.putNumber("Rear RangeFinder Distance", currDistance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished(){
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
