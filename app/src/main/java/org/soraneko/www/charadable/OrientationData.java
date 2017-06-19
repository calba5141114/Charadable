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
 * https://www.youtube.com/watch?v=vqvJn4G3NMM
 */

public class OrientationData implements SensorEventListener
{

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float[] mGravity;
    private float[] mGeomagnetic;
    private float azimut;
    private float pitch;
    private float roll;

    public OrientationData(SensorManager senManager)
    {
        mSensorManager = senManager;
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void onResume()
    {
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause()
    {
        mSensorManager.unregisterListener(this);
    }

    public float getPitch()
    {
        return pitch;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        pitch = sensorEvent.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }


}
