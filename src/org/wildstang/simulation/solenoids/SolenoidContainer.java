/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wildstang.simulation.solenoids;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Rick
 */
public class SolenoidContainer
{

    private static SolenoidContainer object;
    private static final int MODULES = 2;
    private static final int CHANNELS = 8;
    private Solenoid solenoids[][] = new Solenoid[MODULES][CHANNELS];

    public static SolenoidContainer getInstance()
    {
        if (object == null)
        {
            object = new SolenoidContainer();
        }
        return object;
    }

    public void add(Solenoid s, int module, int channel)
    {
        if ((module > solenoids.length) || (module < 1))
        {
            return;
        }
        if ((channel > solenoids[0].length) || (channel < 1))
        {
            return;
        }
        solenoids[module - 1][channel - 1] = s;
    }

    public void update()
    {
        for (int cy = 0; cy < MODULES; cy++)
        {
            for (int cx = 0; cx < CHANNELS; cx++)
            {
                Solenoid s = solenoids[cy][cx];
                if (s == null)
                {
                    continue;
                }
                
                SmartDashboard.putBoolean(String.format("%d - %d", cy + 1, cx + 1), s.get());
            }
        }
    }
}
