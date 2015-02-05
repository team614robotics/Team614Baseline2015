package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import java.lang.Math.*;

/**
 *
 */
public class RAVE_PARTY extends Command {

	double timeout = 0.0;
	//double horizontalValue;
	//double verticalValue;
    public RAVE_PARTY(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	requires(Robot.camera);
    	timeout = time;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double LeftMotorSpeed = ((Math.random() * 2.0) - 1.0) / 5.0;
    	double RightMotorSpeed = ((Math.random() * 2.0) - 1.0) / 5.0;
    	
    	double VerticalValue = Math.random();
    	double HorizontalValue = Math.random();
    	
    	Robot.chassis.motorSpeedDrive(LeftMotorSpeed, LeftMotorSpeed, RightMotorSpeed, RightMotorSpeed);
    	
    	//Robot.camera.setServoPosition(VerticalValue, HorizontalValue);
    	//Robot.camera.logServoPosition();
    	try{
    		this.wait(250);
    	}catch(InterruptedException IE){
    		System.out.println("Could not accomplish designated wait time.");
    	}
    }
    

   

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end(){
    	Robot.camera.setServoPosition(0.5, 0.5);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.camera.setServoPosition(0.5, 0.5);
    }
}
