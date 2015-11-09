package org.wildstang.simulation.gyro;

import org.wildstang.outputmanager.base.IOutputEnum;
import org.wildstang.outputmanager.base.OutputManager;
import org.wildstang.simulation.sensorsimulation.base.ISensorSimulation;
import org.wildstang.yearly.subsystems.DriveBase;
import org.wildstang.yearly.subsystems.base.SubsystemContainer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroSimulation implements ISensorSimulation{
    
    private double left_drive_speed = 0.0;
    private double right_drive_speed = 0.0;
    
    public GyroSimulation() {
        
        left_drive_speed = 0.0;
        right_drive_speed = 0.0;
    }
    
    @Override
    public void init() {
       Gyro gyro = ((DriveBase) (SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX))).getGyro();
       gyro.setAngle(0);
    }
    
    @Override
    public void update() {
        left_drive_speed = ((Double) OutputManager.getInstance().getOutput(OutputManager.LEFT_DRIVE_SPEED_INDEX).get((IOutputEnum) null));
        right_drive_speed = ((Double) OutputManager.getInstance().getOutput(OutputManager.RIGHT_DRIVE_SPEED_INDEX).get((IOutputEnum) null));
        Gyro gyro = ((DriveBase) (SubsystemContainer.getInstance().getSubsystem(SubsystemContainer.DRIVE_BASE_INDEX))).getGyro();
        double angle = gyro.getAngle();
        //Handle brakes
        if ((left_drive_speed > 0.1) && (right_drive_speed < 0.1)) {
            angle--;
        } else if ((left_drive_speed < 0.1) && (right_drive_speed > 0.1)) {
            angle++;
        }
        gyro.setAngle(angle);
        SmartDashboard.putNumber("Gyro Angle", angle);
    }

    
}
