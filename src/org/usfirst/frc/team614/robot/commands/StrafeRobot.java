package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * WE ASSUME THE ROBOT IS ALLIGNED WITH THE BOX.
 * WE ARE NOT AT FAULT FOR ANY DAMAGES ACUMULATED
 * BY THE ROBOT OR ANY FRC PROPERTY
 * ....
 * BLAME JOEY
 */
public class StrafeRobot extends Command {
	
	private double Direction = 0.0;
	private static double lastDirection = 0.0;
	private static double initialAngle = 0.0;
	
    public StrafeRobot(double Degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	requires(Robot.gyroscope);
    	
    	Direction = Degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    	if(lastDirection != Direction){
    		initialAngle = Robot.gyroscope.getGyroAngle();
    		lastDirection = Direction;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	if(Math.abs(Robot.gyroscope.getGyroAngle() - initialAngle) > RobotMap.AUTO_ANGLE_RANGE){
    		if(Robot.gyroscope.getGyroAngle() > initialAngle){
    			Robot.chassis.manualDrive(0.3, Direction, -0.5);
    		}else{
    			Robot.chassis.manualDrive(0.3, Direction, 0.5);
    		}
    	}else{
    		Robot.chassis.manualDrive(0.3, Direction, 0.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished(){
        return true;
    }

    // Called once after isFinished returns true
    protected void end(){
    	Robot.chassis.stopChassis();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted(){
    	Robot.chassis.stopChassis();
    }
}
