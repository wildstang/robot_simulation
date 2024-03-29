/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj;

/**
 * Provide access to the network communication data to / from the Driver
 * Station.
 */
public class DriverStation {
	public enum Alliance { Red, Blue, Invalid }
    

    private static DriverStation instance = new DriverStation();
    private int m_digitalOut;
    private static double[] analogValues = {0.0, 0.0, 0.0, 0.0};
    private static boolean[] digitalValues;

    static {
        digitalValues = new boolean[8];
        for (int i = 0; i < 8; i++) {
            digitalValues[i] = false;
        }
    }

    /**
     * Gets an instance of the DriverStation
     *
     * @return The DriverStation.
     */
    public static DriverStation getInstance() {
        return DriverStation.instance;
    }

    /**
     * DriverStation constructor.
     *
     * The single DriverStation instance is created statically with the instance
     * static member variable.
     */
    protected DriverStation() {
    }

    /**
     * Kill the thread
     */
    public void release() {
    }

    /**
     * Wait for new data from the driver station.
     */
    public void waitForData() {
    }

    /**
     * Copy data from the DS task for the user. If no new data exists, it will
     * just be returned, otherwise the data will be copied from the DS polling
     * loop.
     */
    protected synchronized void getData() {
    }

    /**
     * Copy status data from the DS task for the user. This is used primarily to
     * set digital outputs on the DS.
     */
    protected void setData() {
    }

    /**
     * Read the battery voltage from the specified AnalogChannel.
     *
     * This accessor assumes that the battery voltage is being measured through
     * the voltage divider on an analog breakout.
     *
     * @return The battery voltage.
     */
    public double getBatteryVoltage() {
        // The Analog bumper has a voltage divider on the battery source.
        // Vbatt *--/\/\/\--* Vsample *--/\/\/\--* Gnd
        //         680 Ohms            1000 Ohms
        return 12.0;
    }

    /**
     * Get the value of the axis on a joystick. This depends on the mapping of
     * the joystick connected to the specified port.
     *
     * @param stick The joystick to read.
     * @param axis The analog axis value to read from the joystick.
     * @return The value of the axis on the joystick.
     */
    public double getStickAxis(int stick, int axis) {
        return 0.0;
    }

    /**
     * The state of the buttons on the joystick. 12 buttons (4 msb are unused)
     * from the joystick.
     *
     * @param stick The joystick to read.
     * @return The state of the buttons on the joystick.
     */
    public int getStickButtons(final int stick) {
        return 0;
    }

    /**
     * Get an analog voltage from the Driver Station. The analog values are
     * returned as voltage values for the Driver Station analog inputs. These
     * inputs are typically used for advanced operator interfaces consisting of
     * potentiometers or resistor networks representing values on a rotary
     * switch.
     *
     * @param channel The analog input channel on the driver station to read
     * from. Valid range is 1 - 4.
     * @return The analog voltage on the input.
     */
    public double getAnalogIn(final int channel) {
        if (channel - 1 < analogValues.length) {
            return analogValues[channel - 1];
        }
        return 0.0;
    }

    public void setAnalogIn(final int channel, final double value) {
        if (channel - 1 < analogValues.length) {
            analogValues[channel - 1] = value;
        }
    }

    /**
     * Get values from the digital inputs on the Driver Station. Return digital
     * values from the Drivers Station. These values are typically used for
     * buttons and switches on advanced operator interfaces.
     *
     * @param channel The digital input to get. Valid range is 1 - 8.
     * @return The value of the digital input
     */
    public boolean getDigitalIn(final int channel) {
        if (channel - 1 < digitalValues.length) {
            return digitalValues[channel - 1];
        }
        return false;
    }

    public void setDigitalIn(final int channel, final boolean value) {
        if (channel - 1 < digitalValues.length) {
            digitalValues[channel - 1] = value;
        }
    }

    /**
     * Set a value for the digital outputs on the Driver Station.
     *
     * Control digital outputs on the Drivers Station. These values are
     * typically used for giving feedback on a custom operator station such as
     * LEDs.
     *
     * @param channel The digital output to set. Valid range is 1 - 8.
     * @param value The state to set the digital output.
     */
    public void setDigitalOut(final int channel, final boolean value) {
        m_digitalOut &= ~(0x1 << (channel - 1));
        m_digitalOut |= ((value ? 1 : 0) << (channel - 1));
    }

    /**
     * Get a value that was set for the digital outputs on the Driver Station.
     *
     * @param channel The digital ouput to monitor. Valid range is 1 through 8.
     * @return A digital value being output on the Drivers Station.
     */
    public boolean getDigitalOut(final int channel) {
        return ((m_digitalOut >> (channel - 1)) & 0x1) == 0x1;
    }

    /**
     * Gets a value indicating whether the Driver Station requires the robot to
     * be enabled.
     *
     * @return True if the robot is enabled, false otherwise.
     */
    public boolean isEnabled() {
        return false;
    }

    /**
     * Gets a value indicating whether the Driver Station requires the robot to
     * be disabled.
     *
     * @return True if the robot should be disabled, false otherwise.
     */
    public boolean isDisabled() {
        return false;
    }

    /**
     * Gets a value indicating whether the Driver Station requires the robot to
     * be running in autonomous mode.
     *
     * @return True if autonomous mode should be enabled, false otherwise.
     */
    public boolean isAutonomous() {
        return false;
    }

    /**
     * Gets a value indicating whether the Driver Station requires the robot to
     * be running in test mode.
     *
     * @return True if test mode should be enabled, false otherwise.
     */
    public boolean isTest() {
        return false;
    }

    /**
     * Gets a value indicating whether the Driver Station requires the robot to
     * be running in operator-controlled mode.
     *
     * @return True if operator-controlled mode should be enabled, false
     * otherwise.
     */
    public boolean isOperatorControl() {
        return false;
    }

    /**
     * Has a new control packet from the driver station arrived since the last
     * time this function was called?
     *
     * @return True if the control data has been updated since the last call.
     */
    public synchronized boolean isNewControlData() {
        return false;
    }

    /**
     * Return the DS packet number. The packet number is the index of this set
     * of data returned by the driver station. Each time new data is received,
     * the packet number (included with the sent data) is returned.
     *
     * @return The DS packet number.
     */
    public int getPacketNumber() {
        return 0;
    }

    /**
     * Get the current alliance from the FMS
     *
     * @return the current alliance
     */
    public Alliance getAlliance() {
        return Alliance.Blue;
    }

    /**
     * Gets the location of the team's driver station controls.
     *
     * @return the location of the team's driver station controls: 1, 2, or 3
     */
    public int getLocation() {
        return 0;
    }

    /**
     * Return the team number that the Driver Station is configured for
     *
     * @return The team number
     */
    public int getTeamNumber() {
        return 111;
    }

    

    /**
     * Gets the status data monitor
     *
     * @return The status data monitor for use with IDashboard objects which
     * must send data across the network.
     */
    public Object getStatusDataMonitor() {
        return null;
    }

    /**
     * Increments the internal update number sent across the network along with
     * status data.
     */
    void incrementUpdateNumber() {
    }

    /**
     * Is the driver station attached to a Field Management System? Note: This
     * does not work with the Blue DS.
     *
     * @return True if the robot is competing on a field being controlled by a
     * Field Management System
     */
    public boolean isFMSAttached() {
        return false;
    }

    /**
     * Return the approximate match time The FMS does not currently send the
     * official match time to the robots This returns the time since the enable
     * signal sent from the Driver Station At the beginning of autonomous, the
     * time is reset to 0.0 seconds At the beginning of teleop, the time is
     * reset to +15.0 seconds If the robot is disabled, this returns 0.0 seconds
     * Warning: This is not an official time (so it cannot be used to argue with
     * referees)
     *
     * @return Match time in seconds since the beginning of autonomous
     */
    public double getMatchTime() {
        return Timer.getFPGATimestamp();
    }

    /**
     * Only to be used to tell the Driver Station what code you claim to be
     * executing for diagnostic purposes only
     *
     * @param entering If true, starting disabled code; if false, leaving
     * disabled code
     */
    public void InDisabled(boolean entering) {
    }

    /**
     * Only to be used to tell the Driver Station what code you claim to be
     * executing for diagnostic purposes only
     *
     * @param entering If true, starting autonomous code; if false, leaving
     * autonomous code
     */
    public void InAutonomous(boolean entering) {
    }

    /**
     * Only to be used to tell the Driver Station what code you claim to be
     * executing for diagnostic purposes only
     *
     * @param entering If true, starting teleop code; if false, leaving teleop
     * code
     */
    public void InOperatorControl(boolean entering) {
    }

    /**
     * Only to be used to tell the Driver Station what code you claim to be
     * executing for diagnostic purposes only
     *
     * @param entering If true, starting test code; if false, leaving test code
     */
    public void InTest(boolean entering) {
    }
}
