// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc0.Warbot.subsystems;
import org.usfirst.frc0.Warbot.RobotMap;
import org.usfirst.frc0.Warbot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc0.Warbot.OI;
/**
 *
 */
public class Chassis extends PIDSubsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController speedController2 = RobotMap.chassisSpeedController2;
    SpeedController speedController1 = RobotMap.chassisSpeedController1;
    RobotDrive robotDrive = RobotMap.chassisRobotDrive;
    AnalogChannel leftUltrasonic = RobotMap.chassisLeftUltrasonic;
    AnalogChannel rightUltrasonic = RobotMap.chassisRightUltrasonic;
    Encoder leftEncoder = RobotMap.chassisLeftEncoder;
    Encoder rightEncoder = RobotMap.chassisRightEncoder;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // Initialize your subsystem here
    public Chassis() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        super("Chassis", 1.0, 0.0, 0.0);
        setAbsoluteTolerance(0.2);
        getPIDController().setContinuous(false);
        LiveWindow.addActuator("Chassis", "PIDSubsystem Controller", getPIDController());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new DriveWithJoysticks());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void stop() {
        robotDrive.tankDrive(0,0);
        getPIDController().disable();
        RobotMap.chassisRightEncoder.reset();
        RobotMap.chassisLeftEncoder.reset();
    }
    
    public void DriveWithJoysticks(Joystick left, Joystick right) {
        robotDrive.tankDrive(left, right);
    }
    
   
    
    public void drive(double setpoint) {
        RobotMap.chassisRightEncoder.reset();
        RobotMap.chassisLeftEncoder.reset();
        getPIDController().setSetpoint(setpoint);
        getPIDController().enable();
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        
        return (RobotMap.chassisRightEncoder.pidGet() + RobotMap.chassisLeftEncoder.pidGet()) / 2;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
        speedController1.pidWrite(output);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
    }
}
