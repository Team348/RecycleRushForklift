// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc348.RecycleRushForklift.subsystems;

import org.usfirst.frc348.RecycleRushForklift.Robot;
import org.usfirst.frc348.RecycleRushForklift.RobotMap;
import org.usfirst.frc348.RecycleRushForklift.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Gripper extends PIDSubsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Encoder gripperEncoder = RobotMap.gripperGripperEncoder;
    SpeedController gripperMotor = RobotMap.gripperGripperMotor;
    DigitalInput gripperOpenLimitSwitch = RobotMap.gripperGripperOpenLimitSwitch;
    DigitalInput gripperClosedLimitSwitch = RobotMap.gripperGripperClosedLimitSwitch;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Initialize your subsystem here
    public Gripper() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
        super("Gripper", 1.0, 0.0, 0.0);
        setAbsoluteTolerance(0.5);
        getPIDController().setContinuous(false);
        LiveWindow.addActuator("Gripper", "PIDSubsystem Controller", getPIDController());
        getPIDController().setOutputRange(-0.4, 0.4);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
        
        double p = RobotMap.prefs.getDouble("Gripper_P", 0.08);
        double i = RobotMap.prefs.getDouble("Gripper_I", 0.004);
        double d = RobotMap.prefs.getDouble("Gripper_D", 0.0);
        
       getPIDController().setPID(p, i, d);
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new StopGripperCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
        return gripperEncoder.pidGet();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
        gripperMotor.pidWrite(output);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
        
    }
    
    public void Open()
    {
    	gripperMotor.set(0.25);
    }
    
    public void Close()
    {
    	gripperMotor.set(-0.6);
    }
    
    public void Stop()
    {
    	gripperMotor.set(0);
    }
    
    public double getGripperMotorCurrent() {
    	return Robot.drivePlatform.getCurrent(RobotMap.kGripperMotorPowerChannel);
    }
    
    public boolean ShouldStopInward() {
    	double limitCurrent = RobotMap.prefs.getDouble("Gripper_CurrentLimit", 25);
    	
    	return getGripperMotorCurrent() > limitCurrent || gripperClosedLimitSwitch.get();
    }
    
    public boolean ShouldStopOutward() {
    	double limitCurrent = RobotMap.prefs.getDouble("Gripper_CurrentLimit", 10);
    	
    	return getGripperMotorCurrent() > limitCurrent;
    }
    
    public void ServiceCurrentMonitoring() {
    	if(gripperMotor.get() < 0 && ShouldStopInward())
    	{
    		// we are stalled or at limit
        	disable();
        	gripperMotor.set(0);
    	}
    	else if(gripperMotor.get() > 0 && ShouldStopOutward())
        {
        	// we are stalled
        	disable();
        	gripperMotor.set(0);
        }
        SmartDashboard.putNumber("GripperMotorCurrent", getGripperMotorCurrent());
    }
}
