
package org.usfirst.frc.team614.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team614.robot.commands.AutonomousBarrel_CG;
import org.usfirst.frc.team614.robot.subsystems.Accelerometer;
import org.usfirst.frc.team614.robot.subsystems.Camera;
import org.usfirst.frc.team614.robot.subsystems.Chassis;
import org.usfirst.frc.team614.robot.subsystems.Pneumatics;
import org.usfirst.frc.team614.robot.subsystems.PowerPanel;
import org.usfirst.frc.team614.robot.subsystems.RangeFinder;
import org.usfirst.frc.team614.robot.subsystems.Gyroscope;
import org.usfirst.frc.team614.robot.subsystems.Winch;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final Chassis chassis = new Chassis();
	public static final RangeFinder rangefinder = new RangeFinder();
	public static final Accelerometer accelerometer = new Accelerometer();
	public static Gyroscope gyroscope = new Gyroscope();
	public static final Winch winch  = new Winch();
	public static final Pneumatics pneumatics = new Pneumatics(); //new Pneumatics();
	public static final PowerPanel powerpanel = null;//new PowerPanel();
	//public static final Camera camera = null;//new Camera(); null for when it's hooked up to the new robot. If not, set it equal to new Camera()
	public static OI oi;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousBarrel_CG();
		//autonomousCommand = new AutonomousCommand_CG();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
