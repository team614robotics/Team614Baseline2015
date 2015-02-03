package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {
	
	private double distanceToDrive = 0.0;
	private double initialAngle = 0.0;
	private boolean driveForward;
	private boolean isDone;
	private double Timeout;
	
    public DriveDistance(double Distance, boolean Forward, double Time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	requires(Robot.gyroscope);
    	/*double averageCurrDistance = 0.0;
    	for(int i = 0; i < 4; i++){
    		averageCurrDistance += Robot.chassis.getEncoderDistance(i);
    	}
    	averageCurrDistance /= 4;*/
    	distanceToDrive =  Robot.chassis.getEncoderDistance(0) + Distance;
    	driveForward = Forward;
    	Timeout = Time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.stopChassis();
    	Robot.chassis.clearAssisted();
    	if(driveForward){
			Robot.chassis.toggleAssistedForward();
		}else{
			Robot.chassis.toggleAssistedBackward();
		}
    	initialAngle = (Robot.gyroscope.getAngle() % 360.0);
    	setTimeout(Timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	/*double averageCurrDistance = 0.0;
    	for(int i = 0; i < 4; i++){
    		averageCurrDistance += Robot.chassis.getEncoderDistance(i);
    	}*/
    	while(Robot.chassis.getEncoderDistance(0) < distanceToDrive){
    		double currAngleOffset = (Robot.gyroscope.getAngle() % 360);
    		
    		/**
    		 * If currAngleOffset is within the range specified, it becomes 0.
    		 * Otherwise, return currAngleOffset as a number between -360 and 360
    		 */
    		currAngleOffset = (currAngleOffset < (initialAngle + RobotMap.AUTO_ANGLE_RANGE) && 
    						   currAngleOffset > (initialAngle - RobotMap.AUTO_ANGLE_RANGE) ? 0 : 
    						   currAngleOffset);
    		
    		/**
    		 * Since Rotation needs a value on [-1.0,1.0], we
    		 * divide the angle by 360, and invert it.
    		 * 
    		 * The reason we invert it is so we will rotate opposite
    		 * of the angle, so it will decrease in magnitude
    		 */
    		double AngleRotation = -(currAngleOffset / 360.0);
    		
    		Robot.chassis.manualDrive(RobotMap.AUTO_MOTOR_MAGNITUDE, 0.0, AngleRotation);
    	}
    	isDone = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end(){
    	Robot.chassis.stopChassis();
    	Robot.chassis.clearAssisted();
    	Robot.gyroscope.resetGyro();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted(){
    	Robot.chassis.stopChassis();
    	Robot.chassis.clearAssisted();
    	Robot.gyroscope.resetGyro();
    }
}
