package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GetAngle extends Command {
	
	private boolean isDone;
	private double GyroAngle;
	private long lastTime;
	
    public GetAngle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gyroscope);
    	requires(Robot.accelerometer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isDone = false;
    	GyroAngle = Robot.gyroscope.getAngle();
    	lastTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//SmartDashboard.putNumber("GyroScope Angle: ", Robot.gyroscope.getAngle()); 
    			//AccelerometerAngle is the resulting angle from the current speed in the y; AKA the resulting vector angle
    	SmartDashboard.putNumber("Degrees: ", Robot.gyroscope.getAngle());
    	double AccelerometerAngle = Math.toDegrees(Math.atan2(Robot.accelerometer.getSpeedY(), Robot.accelerometer.getSpeedX()));//Math.sqrt((Math.pow(Robot.accelerometer.getSpeedX(), 2)) + (Math.pow(Robot.accelerometer.getSpeedY(), 2)));
    	double deltaTime = System.currentTimeMillis() - lastTime;
    	GyroAngle = (0.98 * (GyroAngle + (Robot.gyroscope.getAngle() * deltaTime))) + (0.02 * AccelerometerAngle); //these two constants (.98 & .002 a.t.m.) must add up to 1
    	SmartDashboard.putNumber("GyroScope Angle: ", GyroAngle);
    	lastTime = System.currentTimeMillis();
    	//isDone = true;
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
