package domain_try.sensor_actions;

import android.hardware.Sensor;
import android.hardware.SensorManager;

/**
 * Created by himanshu on 10/21/2015.
 */
public class MotionDetector implements Runnable {
    private SensorManager mSensorManager;
    private Sensor mSensor;

    public MotionDetector(SensorManager sm) {
        this.mSensorManager = sm;
    }

    @Override
    public void run() {
        try {
            //  mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            GyroListener list = new GyroListener();
            mSensorManager.registerListener(list, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
