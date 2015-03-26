package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistance extends Command {
	
	private final boolean gyroDrive = false;
	private final double MOTOR_REDUCTION_SCALE = 0.9;
	
	//INITIAL ANGLE OF THE ROBOT(FOR USE IN ANGLE CORRECTIONS)
	private double initialAngle = 0.0;
	
	//Holds the Encoder Value to kill at(+ = Forwards/ - = Backwards)
	private double DriveDistance = 0.0;
	private boolean driveForward = true;
	
	//Timeout/Ending Variables
	private boolean isDone;
	private double Timeout;
	
    public DriveDistance(double Distance, boolean Forward, double Time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	requires(Robot.gyroscope);
    	
    	DriveDistance = Distance;
    	driveForward = Forward;
    	Timeout = Time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.stopChassis();
    	initialAngle = (Robot.gyroscope.getGyroAngle() % 360.0);
    	
    	if(driveForward){
    		DriveDistance = Robot.chassis.getAverageDistance() + DriveDistance;
    	}else{
    		DriveDistance = Robot.chassis.getAverageDistance() - DriveDistance;
    	}
    	
    	setTimeout(Timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	
    	double currAngle = Robot.gyroscope.getGyroAngle() % 360.0;
    	
    	/**
		 * If currAngle is within the range specified, it becomes 0.
		 * Otherwise, return currAngle as a number between -360 and 360
		 */
		currAngle = (currAngle < (initialAngle + RobotMap.AUTO_ANGLE_RANGE) && 
				     currAngle > (initialAngle - RobotMap.AUTO_ANGLE_RANGE) ? 0 : 
					 currAngle);
		
		double AngleRotation = -(currAngle / 360.0);
		double MotorSpeed = RobotMap.AUTO_MOTOR_MAGNITUDE;
		if(!driveForward){
			MotorSpeed *= -1.0;
		}
		
		if(gyroDrive){
			Robot.chassis.manualDrive(MotorSpeed, 0.0, AngleRotation);
		}else{
			double LeftMotorSpeed = RobotMap.AUTO_MOTOR_MAGNITUDE;
			double RightMotorSpeed = RobotMap.AUTO_MOTOR_MAGNITUDE;
			
			if(currAngle < 0){
				LeftMotorSpeed *= MOTOR_REDUCTION_SCALE;
			}else{
				RightMotorSpeed *= MOTOR_REDUCTION_SCALE;
			}
			Robot.chassis.motorSpeedDrive(LeftMotorSpeed, LeftMotorSpeed, 
					                      RightMotorSpeed, RightMotorSpeed);
		}
    	
    	if(driveForward){
    		if(Robot.chassis.getAverageDistance() > DriveDistance){
    			isDone = true;
    		}
    	}else{
    		if(Robot.chassis.getAverageDistance() < DriveDistance){
    			isDone = true;
    		}
    	}
    	
    	/*boolean drivenDistance = false;
    	while(!drivenDistance){
    		double currAngleOffset = (Robot.gyroscope.getAngle() % 360);
    		
    		/**
    		 * If currAngleOffset is within the range specified, it becomes 0.
    		 * Otherwise, return currAngleOffset as a number between -360 and 360
    		 */
    		/*currAngleOffset = (currAngleOffset < (initialAngle + RobotMap.AUTO_ANGLE_RANGE) && 
    						   currAngleOffset > (initialAngle - RobotMap.AUTO_ANGLE_RANGE) ? 0 : 
    						   currAngleOffset);
    		
    		if(!manualSpeedMode){
	    		/**
	    		 * Since Rotation needs a value on [-1.0,1.0], we
	    		 * divide the angle by 360, and invert it.
	    		 * 
	    		 * The reason we invert it is so we will rotate opposite
	    		 * of the angle, so it will decrease in magnitude
	    		 */
	    		/*double AngleRotation = -(currAngleOffset / 360.0);
	    		
	    		Robot.chassis.manualDrive(RobotMap.AUTO_MOTOR_MAGNITUDE, 0.0, AngleRotation);
    		}else{
    			double LeftMotorSpeed = RobotMap.AUTO_MOTOR_MAGNITUDE;
    			double RightMotorSpeed = RobotMap.AUTO_MOTOR_MAGNITUDE;
    			
    			if(currAngleOffset < 0){
    				LeftMotorSpeed *= MOTOR_REDUCTION_SCALE;
    			}else{
    				RightMotorSpeed *= MOTOR_REDUCTION_SCALE;
    			}
    			Robot.chassis.motorSpeedDrive(LeftMotorSpeed, LeftMotorSpeed, 
    					                      RightMotorSpeed, RightMotorSpeed);
    		}
    		drivenDistance = (driveForward ? Robot.chassis.getEncoderDistance(currEncoder) < distanceToDrive: 
    			              Robot.chassis.getEncoderDistance(currEncoder) > distanceToDrive);
    	}
    	isDone = drivenDistance;*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end(){
    	Robot.chassis.stopChassis();
    	//Robot.gyroscope.resetGyro();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted(){
    	Robot.chassis.stopChassis();
    	//Robot.gyroscope.resetGyro();
    }
}
