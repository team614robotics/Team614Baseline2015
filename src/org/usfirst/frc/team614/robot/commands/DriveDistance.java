package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {
	
	private double distanceToDrive = 0.0;
	private boolean driveForward;
	private boolean isDone;
	private double Timeout;
	
    public DriveDistance(double Distance, boolean Forward, double Time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
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
    	setTimeout(Timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	/*double averageCurrDistance = 0.0;
    	for(int i = 0; i < 4; i++){
    		averageCurrDistance += Robot.chassis.getEncoderDistance(i);
    	}*/
    	while(Robot.chassis.getEncoderDistance(0) < distanceToDrive){
    		Robot.chassis.manualDrive(0.0, 0.0, 0.0); //(???, Degrees, Rotation) set at 0.0 for default
    	}
    	isDone = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
