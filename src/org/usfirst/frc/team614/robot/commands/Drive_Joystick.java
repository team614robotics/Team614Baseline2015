package org.usfirst.frc.team614.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team614.robot.Robot;
import org.usfirst.frc.team614.robot.RobotMap;
/**
 *
 */
public class Drive_Joystick extends Command {

    public Drive_Joystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	if(RobotMap.DRIVE_MODE == RobotMap.DriveMode.TANK_DRIVE){
    		Robot.chassis.tankDriveMode(Robot.oi.getPrimaryJoystick());
    	}else if(RobotMap.DRIVE_MODE == RobotMap.DriveMode.ARCADE_DRIVE){
    		Robot.chassis.arcadeDriveMode(Robot.oi.getPrimaryJoystick());
    	}else{
    		Robot.chassis.mecanumDriveMode(Robot.oi.getPrimaryJoystick());
    		//Robot.chassis.manualDrive(0.5, 90, 0.0);
    	}
    	Robot.chassis.logData();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
