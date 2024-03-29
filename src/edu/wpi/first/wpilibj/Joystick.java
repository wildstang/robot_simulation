/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj;

import org.wildstang.joystick.IJoystick;
import org.wildstang.joystick.IHardwareJoystick;
import org.wildstang.joystick.OnscreenJoystick;
import org.wildstang.simulation.Simulation;
import org.wildstang.joystick.HardwareJoystick;


public class Joystick {

    IJoystick joystick;
    boolean isHardwareJoystick;

    public Joystick(int channel) {
    	if (channel >= 0 && channel <= 1)
    	{
    		joystick = Simulation.getInstance().getJoystick(channel);
    		isHardwareJoystick = Simulation.getInstance().isJoystickHw(channel);
    	}
    	else
    	{
    		System.out.println("Joystick channel: " + channel + "is not supported in simulation.");
    	}
    }

    public boolean getRawButton(int but) {
        return joystick.getRawButton(but);
    }

    public boolean getTrigger() {
        return joystick.getTrigger();
    }

    public double getX() {
        return joystick.getX();
    }

    public double getY() {
        return joystick.getY();
    }

    public double getZ() {
        return joystick.getZ();
    }

    public double getTwist() {
        return joystick.getTwist();
    }

    public double getThrottle() {
        return joystick.getThrottle();
    }

    public double getRawAxis(int axisEnum) {
        return joystick.getRawAxis(axisEnum);
    }

    public void pullData() {
        if (joystick instanceof IHardwareJoystick) {
            ((IHardwareJoystick) joystick).pullData();
        }
    }

    public int getAxisChannel(AxisType axis) {
        return joystick.getAxisChannel(axis);
    }

    /**
     * Set the channel associated with a specified axis.
     *
     * @param axis The axis to set the channel for.
     * @param channel The channel to set the axis to.
     */
    public void setAxisChannel(AxisType axis, int channel) {
        joystick.setAxisChannel(axis, channel);
    }

    public static class AxisType {

        /**
         * The integer value representing this enumeration
         */
        public final int value;
        static final int kX_val = 0;
        static final int kY_val = 1;
        static final int kZ_val = 2;
        static final int kTwist_val = 3;
        static final int kThrottle_val = 4;
        static final int kNumAxis_val = 5;
        /**
         * axis: x-axis
         */
        public static final AxisType kX = new AxisType(kX_val);
        /**
         * axis: y-axis
         */
        public static final AxisType kY = new AxisType(kY_val);
        /**
         * axis: z-axis
         */
        public static final AxisType kZ = new AxisType(kZ_val);
        /**
         * axis: twist
         */
        public static final AxisType kTwist = new AxisType(kTwist_val);
        /**
         * axis: throttle
         */
        public static final AxisType kThrottle = new AxisType(kThrottle_val);
        /**
         * axis: number of axis
         */
        public static final AxisType kNumAxis = new AxisType(kNumAxis_val);

        private AxisType(int value) {
            this.value = value;
        }
    }
    
    public boolean isHardwareJoystick() {
        return isHardwareJoystick;
    }

}
