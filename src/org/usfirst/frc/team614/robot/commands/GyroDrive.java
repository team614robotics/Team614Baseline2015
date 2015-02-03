package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GyroDrive extends Command {
	
	private double degreeLimit;
	private double timeout;
	private boolean isDone;

    public GyroDrive(double dL, double Time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	requires(Robot.gyroscope);
    	degreeLimit = dL;
    	timeout = Time;
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    	isDone = false;
    	setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	double gyroAngle = Robot.gyroscope.getAngle() % 360;
    	while(gyroAngle < degreeLimit && gyroAngle > -degreeLimit){
    		SmartDashboard.putNumber("GyroScope Angle: ", gyroAngle);
    		gyroAngle = Robot.gyroscope.getAngle() % 360;
    		Robot.chassis.manualDrive(1.0, 0, 0.0);
    	}
    	isDone = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopChassis();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.chassis.stopChassis();

    }
}
