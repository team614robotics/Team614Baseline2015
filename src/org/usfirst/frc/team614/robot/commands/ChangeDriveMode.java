package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ChangeDriveMode extends Command {
	
	private boolean isDone;
	
    public ChangeDriveMode() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    	isDone = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.DriveMode currMode = RobotMap.DRIVE_MODE;
    	System.out.println(currMode + ": " + currMode.ordinal());
    	System.out.println("Drive Modes: " + RobotMap.DriveMode.values().length);
    	if(currMode.ordinal() == RobotMap.DriveMode.values().length - 1){	//If currMode is the last available mode in the enum DriveMode
    		currMode = RobotMap.DriveMode.values()[0];						//It becomes the first available mode
    	}else{
    		currMode = RobotMap.DriveMode.values()[currMode.ordinal() + 1]; //Otherwise, it goes to the next mode
    	}
    	
    	switch(currMode.ordinal()){
    		case 0:			//TANK DRIVE
    			SmartDashboard.putString("Drive Mode: ", "Tank");
    			break;
    		case 1:			//ARCADE DRIVE
    			SmartDashboard.putString("Drive Mode: ", "Arcade");
    			break;
    		case 2:			//MECANUM DRIVE
    			SmartDashboard.putString("Drive Mode: ", "Mecanum");
    			break;
    	}
    	
    	RobotMap.DRIVE_MODE = currMode;
    	
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
