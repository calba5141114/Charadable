package org.soraneko.www.charadable;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.SyncStateContract;

/**
 * Created by Eric on 6/18/2017.
 * Gets information from the devices accelerometer
 * https://www.youtube.com/watch?v=vqvJn4G3NMM
 */

public class OrientationData implements SensorEventListener
{

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mMagnometer;
    private float pitch;
    private final float[] mAccelerometerReading = new float[3];
    private final float[] mMagnetometerReading = new float[3];

    private final float[] mRotationMatrix = new float[9];
    private final float[] mOrientationAngles = new float[3];

    /**
     * Default constructor of the class
     * @param senManager Initialized SensorManager object
     */
    public OrientationData(SensorManager senManager)
    {
        mSensorManager = senManager;
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //mMagnometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    /**
     * Registers listener on accelerometer
     */
    public void onResume()
    {
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        //mSensorManager.registerListener(this, mMagnometer, SensorManager.SENSOR_DELAY_GAME);
    }

    /**
     * Unregisters listener on accelerometer
     */
    public void onPause()
    {
        mSensorManager.unregisterListener(this);
    }

    /**
     * Gets the pitch acceleration of the device
     * @return A float with the pitch acceleration (in m/s^2) of the device
     */
    public float getPitch()
    {
        /*
        mSensorManager.getRotationMatrix(mRotationMatrix, null,
                mAccelerometerReading, mMagnetometerReading);

        mSensorManager.getOrientation(mRotationMatrix, mOrientationAngles);

        pitch = mOrientationAngles[1];
        */

        return pitch;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        /*
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(sensorEvent.values, 0, mAccelerometerReading,
                    0, mAccelerometerReading.length);
        }
        else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(sensorEvent.values, 0, mMagnetometerReading,
                    0, mMagnetometerReading.length);
        }
        */
        pitch = sensorEvent.values[2];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }


}
