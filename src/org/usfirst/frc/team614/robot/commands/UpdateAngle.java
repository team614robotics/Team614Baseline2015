package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateAngle extends Command {
	
	private double GyroAngle;
	private long lastTime;
	
    public UpdateAngle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gyroscope);
    	requires(Robot.accelerometer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	GyroAngle = Robot.gyroscope.getGyroAngle();
    	lastTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    
    /**
     * The Complimentary Filter requires two values:
     *     - An Angle Value of the Accelerometer (X and Y Planes)
     *     - A GyroScope rate
     *     
     * From these values, we have two constants, in this case 0.98 and 0.02, essentially bias
     * values for the calculation. We then integrate the gyroscope rate, add it to the previous value,
     * and then add the accelerometer data. This becomes the new angle and sent back
     * to the gyro for record keeping.
     */
    protected void execute() {
    	
    	double AccelerometerAngle = Math.toDegrees(Math.atan2(Robot.accelerometer.getSpeedY(), Robot.accelerometer.getSpeedX()));//Math.sqrt((Math.pow(Robot.accelerometer.getSpeedX(), 2)) + (Math.pow(Robot.accelerometer.getSpeedY(), 2)));
    	
    	double deltaTime = System.currentTimeMillis() - lastTime;
    	
    	GyroAngle = (0.98 * (GyroAngle + (Robot.gyroscope.getGyroRate() * deltaTime)))
    			     + (0.02 * AccelerometerAngle); //these two constants (.98 & .002 a.t.m.) must add up to 1
    	//SmartDashboard.putNumber("GyroScope Angle: ", GyroAngle);
    	Robot.gyroscope.setGyroAngle(GyroAngle);
    	lastTime = System.currentTimeMillis();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end(){
    	Robot.gyroscope.setGyroAngle(GyroAngle);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.gyroscope.setGyroAngle(GyroAngle);
    }
}
