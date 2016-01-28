
package org.usfirst.frc.team4787.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4787.robot.commands.ExampleCommand;
import org.usfirst.frc.team4787.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;

	 Joystick mechstick; JoystickButton trigger;
	    final int  flywheel1_PWM = 9, flywheel2_PWM = 8, angler1_PWM = 7, angler2_PWM = 6, MECHSTICK_NUM = 1, UP_LIMIT = 0, DN_LIMIT = 1; // PWM, Stick, and Limiter ports
	    //name PWMs ^^
	    final double TEST_TIMEOUT = .5;
	    final int TRIGGER_BTN = 1;
	    int motorSwitch = 0; // Current motor for testing
	    final double DEADZONEX = 0.08, DEADZONEY = 0.08, DEADZONEMECH = 0.06; //Deadzones
	    double lastTime = 0, expX = 0, expY = 0, x = 0, y = 0, z = 0, mechX = 0, mechY = 0, mechZ = 0; // Motor powers

	    Jaguar fly1 = new Jaguar(flywheel1_PWM);
	    Jaguar fly2 = new Jaguar(flywheel2_PWM);
	    Jaguar angler1 = new Jaguar(angler1_PWM);
	    Jaguar angler2 = new Jaguar(angler2_PWM);
	    
	public Robot()
	{
    	mechstick = new Joystick(MECHSTICK_NUM);
    	trigger = new JoystickButton(mechstick, 1);
    	
    	//when stick is moved out of deadzones, provide power to motor to move up/down
    	
    	double mechx = mechstick.getX();
    	double mechy = mechstick.getY();
    	if(Math.abs(mechy) > DEADZONEY) 
    	{
    		fly1.set(-mechy);
    	}
    		
    	//while trigger is pulled, spin flywheels
	}
	    
	    
	    
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
        autonomousCommand = new ExampleCommand();
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
        
        //USER-WRITTEN CODE:
        
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


