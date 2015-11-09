package org.wildstang.simulation.sensorsimulation.base;

import java.util.ArrayList;
import org.wildstang.simulation.gyro.GyroSimulation;
import org.wildstang.simulation.encoders.DriveBaseEncoders;
/**
 *
 * @author Nathan
 */
public class SensorSimulationContainer {

    private static SensorSimulationContainer instance = null;
    private static ArrayList sensorSimulation = new ArrayList(10);

    public static SensorSimulationContainer getInstance() {
        if (SensorSimulationContainer.instance == null) {
            SensorSimulationContainer.instance = new SensorSimulationContainer();
        }
        return SensorSimulationContainer.instance;
    }

    public void init() {
        for (int i = 0; i < sensorSimulation.size(); i++) {
            ISensorSimulation sys = (ISensorSimulation) sensorSimulation.get(i);
            if(sys != null) sys.init();
        }
    }

    public void update() {
        for (int i = 0; i < sensorSimulation.size(); i++) {
            ISensorSimulation sys = (ISensorSimulation) sensorSimulation.get(i);
            if(sys != null) sys.update();
        }
    }
    /**
     * Constructor for the subsystem container.
     *
     * Each new subsystem must be added here. This is where they are
     * instantiated as well as placed in the subsystem container.
     */
    protected SensorSimulationContainer() {
      sensorSimulation.add(new GyroSimulation());
      sensorSimulation.add(new DriveBaseEncoders());
    }
}
