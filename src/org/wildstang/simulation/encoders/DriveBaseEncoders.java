
package org.wildstang.simulation.encoders;

import com.wildstangs.outputmanager.base.IOutputEnum;
import com.wildstangs.outputmanager.base.OutputManager;
import com.wildstangs.simulation.sensorsimulation.base.ISensorSimulation;
import com.wildstangs.subsystems.DriveBase;
import com.wildstangs.subsystems.base.SubsystemContainer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveBaseEncoders implements ISensorSimulation{
    private double desired_left_drive_speed = 0.0;
    private double desired_right_drive_speed = 0.0;
    private double actual_left_drive_speed = 0.0;
    private double actual_right_drive_speed = 0.0;
    private double previousTime = 0.0;
    
    //Set top speed to 19.2 ft/ second = 230.4 inches / second = 4.608 inches/ 20 ms 
    private static final double MAX_SPEED_INCHES_HIGHGEAR = 4.608; 
    //Set low gear top speed to 8.5 ft/ second = 102 inches / second = 2.04 inches/ 20 ms 
    private static final double MAX_SPEED_INCHES_LOWGEAR = 2.04; 
    private static final double MAX_SPEED_INCHES = MAX_SPEED_INCHES_LOWGEAR; 
    private static final double WHEEL_DIAMETER = 4.25; 
    private static final double GEAR_RATIO = 5.8333; 
    private static final double ENCODER_TICKS_PER_INCH = ((360.0*GEAR_RATIO)/(WHEEL_DIAMETER*Math.PI)); 
    
    //Following are inches per 20 ms
    private static final double VICTOR_BRAKES_EFFECT_DECELERATION = ((50.0/2000)*20);; 
    //Say it takes two seconds to get to top speed
    private static final double ACCELERATION_PER_FRAME = (100.0/1000*20); 

    private static final boolean doModelWithCappedAcceleration = true; 
        
    public DriveBaseEncoders() {
        
        desired_left_drive_speed = 0.0;
        desired_right_drive_speed = 0.0;
        actual_left_drive_speed = 0.0;
        actual_right_drive_speed = 0.0;
        
        //Create graphs for 
    }
    @Override
    public void init() {
        desired_left_drive_speed = 0.0;
        desired_right_drive_speed = 0.0;
        actual_left_drive_speed = 0.0;
        actual_right_drive_speed = 0.0;
    }
    public void update (){ 
        desired_left_drive_speed = ((Double) OutputManager.getInstance().getOutput(OutputManager.LEFT_DRIVE_SPEED_INDEX).get((IOutputEnum) null));
        desired_right_drive_speed = ((Double) OutputManager.getInstance().getOutput(OutputManager.RIGHT_DRIVE_SPEED_INDEX).get((IOutputEnum) null));
        double percentagePower = desired_left_drive_speed;
        //Convert victor -1 to 1 to -2.04 inches per 20 ms to 2.04
        desired_left_drive_speed *= MAX_SPEED_INCHES;
        desired_right_drive_speed *= MAX_SPEED_INCHES;
        double previousLeftSpeed = actual_left_drive_speed; 
        double previousRightSpeed = actual_right_drive_speed; 
         
       if (doModelWithCappedAcceleration){
            
            double diff = desired_left_drive_speed - actual_left_drive_speed; 
            //Handle brakes
            if ((desired_left_drive_speed > -0.01) && (desired_left_drive_speed < 0.01)){
                if (actual_left_drive_speed > 0.0){
                    actual_left_drive_speed -= VICTOR_BRAKES_EFFECT_DECELERATION; 
                    if (actual_left_drive_speed < 0){
                        actual_left_drive_speed = 0 ; 
                    }
                } else { 
                    actual_left_drive_speed += VICTOR_BRAKES_EFFECT_DECELERATION; 
                    if (actual_left_drive_speed > 0){
                        actual_left_drive_speed = 0 ; 
                    }
                }
            }
            else if (diff > 0.05  ){
                actual_left_drive_speed += ACCELERATION_PER_FRAME; 
                if (actual_left_drive_speed > MAX_SPEED_INCHES){
                    actual_left_drive_speed = MAX_SPEED_INCHES; 
                }
                if (actual_left_drive_speed > desired_left_drive_speed){
                    actual_left_drive_speed = desired_left_drive_speed; 
                }
            } else if (diff < -0.05  ){
                actual_left_drive_speed -= ACCELERATION_PER_FRAME; 
                if (actual_left_drive_speed < (-1*MAX_SPEED_INCHES)){
                    actual_left_drive_speed = (-1*MAX_SPEED_INCHES); 
                }
                if (actual_left_drive_speed < desired_left_drive_speed){
                    actual_left_drive_speed = desired_left_drive_speed; 
                }
            } else {
            }

            //Handle Right side
            double diffRight = desired_right_drive_speed - actual_right_drive_speed; 
            //Handle brakes
            if ((desired_right_drive_speed > -0.01) && (desired_right_drive_speed < 0.01)){
                if (actual_right_drive_speed > 0.0){
                    actual_right_drive_speed -= VICTOR_BRAKES_EFFECT_DECELERATION; 
                    if (actual_right_drive_speed < 0){
                        actual_right_drive_speed = 0 ; 
                    }
                } else { 
                    actual_right_drive_speed += VICTOR_BRAKES_EFFECT_DECELERATION; 
                    if (actual_right_drive_speed > 0){
                        actual_right_drive_speed = 0 ; 
                    }
                }
            }
            else if (diffRight > 0.05  ){
                actual_right_drive_speed += ACCELERATION_PER_FRAME;
                if (actual_right_drive_speed > MAX_SPEED_INCHES){
                    actual_right_drive_speed = MAX_SPEED_INCHES; 
                }
                if (actual_right_drive_speed > desired_right_drive_speed){
                    actual_right_drive_speed = desired_right_drive_speed; 
                }
            } else if (diffRight < -0.05  ){
                actual_right_drive_speed -= ACCELERATION_PER_FRAME; 
                if (actual_right_drive_speed< (-1*MAX_SPEED_INCHES)){
                    actual_right_drive_speed = (-1*MAX_SPEED_INCHES); 
                }
                if (actual_right_drive_speed < desired_right_drive_speed){
                    actual_right_drive_speed = desired_right_drive_speed; 
                }
            } else {
            }

        } else { 
            //Perfect controller matches desired speed
            actual_right_drive_speed = desired_right_drive_speed; 
            actual_left_drive_speed = desired_left_drive_speed; 

        }
        
        
        int left_encoder = ((DriveBase) SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX)).getLeftEncoder().get(); 
        int right_encoder = ((DriveBase) SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX)).getRightEncoder().get(); 
        //Compensate for variable period
        int left_encoder_velocity_increment = 0 ; 
        int right_encoder_velocity_increment = 0; 
        int left_encoder_acc_increment; 
        int right_encoder_acc_increment; 
        double currTime = Timer.getFPGATimestamp(); 
        double deltaTime = currTime - previousTime; 
        if (Math.abs(actual_right_drive_speed) > 0.001){
            right_encoder_velocity_increment = (int)Math.ceil((previousRightSpeed *ENCODER_TICKS_PER_INCH * deltaTime) / 0.020);
            right_encoder_acc_increment = (int)Math.ceil(((actual_right_drive_speed - previousRightSpeed) *ENCODER_TICKS_PER_INCH * deltaTime) / 0.020 / 2);
            right_encoder+= right_encoder_velocity_increment + right_encoder_acc_increment;
        }
        if (Math.abs(actual_left_drive_speed) > 0.001){
            left_encoder_velocity_increment = (int)Math.ceil((previousLeftSpeed *ENCODER_TICKS_PER_INCH * deltaTime) / 0.020);
            left_encoder_acc_increment = (int)Math.ceil(((actual_left_drive_speed - previousLeftSpeed) *ENCODER_TICKS_PER_INCH * deltaTime) / 0.020 / 2);
            left_encoder+= left_encoder_velocity_increment + left_encoder_acc_increment;
        }
        previousTime = currTime; 
        
        ((DriveBase) SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX)).getLeftEncoder().set(left_encoder);
        ((DriveBase) SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX)).getRightEncoder().set(right_encoder);

        if (Math.abs(actual_left_drive_speed) > 0.01){
//            Logger.getLogger().debug(this.getClass().getName(), "KinematicsSimulation", "lDS: " + actual_left_drive_speed + " %: " + percentagePower + " rDS: " + actual_right_drive_speed + " dle: " + left_encoder_velocity_increment + " dre: " + right_encoder_velocity_increment);
//            DriveBase db = ((DriveBase) SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX));
//            Logger.getLogger().debug(this.getClass().getName(), "SpeedPid", "value: " + db.getPidSpeedValue() + " error: " + db.getSpeedError() + " posError: " + db.getDeltaPosError() + " \n ");
        }

        SmartDashboard.putNumber("Actual Left Speed", actual_left_drive_speed); 
        SmartDashboard.putNumber("Actual Right Speed", actual_right_drive_speed); 
        SmartDashboard.putNumber("Speed PID Error", ((DriveBase) SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX)).getSpeedError());
        SmartDashboard.putNumber("Speed PID Value", ((DriveBase) SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX)).getPidSpeedValue());
        SmartDashboard.putNumber("Distance Error", ((DriveBase) SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX)).getDeltaPosError());
        SmartDashboard.putNumber("Measured Velocity", ((DriveBase) SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX)).getVelocity());
        SmartDashboard.putNumber("Measured Acceleration", ((DriveBase) SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX)).getAcceleration());
        
        SmartDashboard.putNumber("Left Distance", ((DriveBase) SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX)).getLeftDistance()); 
        SmartDashboard.putNumber("Right Distance", ((DriveBase) SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX)).getRightDistance());
        
    }

    
    

}
