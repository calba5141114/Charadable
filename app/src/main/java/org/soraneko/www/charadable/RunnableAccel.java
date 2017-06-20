package org.soraneko.www.charadable;

import android.util.Log;

/**
 * Created by Eric on 6/18/2017.
 * Used to check on the pitch acceleration (m/s^2) of the device
 * https://www.tutorialspoint.com/java/java_multithreading.htm
 */
public class RunnableAccel implements Runnable
{

    private OrientationData orientationData;
    private volatile boolean running = true;
    private float pitch;
    private Thread t;

    /**
     * Constructor of the RunnableAccel
     * @param orienData An initialized OrientationData object
     */
    public RunnableAccel(OrientationData orienData)
    {
        orientationData = orienData;
        Log.e("E", "Accelerameter Thread Object Created");
    }

    /**
     * Starts the RunnableAccel thread
     */
    public void start()
    {
        Log.e("E", "Starting an accelerameter thread");
        if (t == null)
        {
            t = new Thread(this, "AccelThread");
            t.start();
        }

    }

    /**
     * Stops the RunnableAccel thread
     */
    public void stop()
    {
        running = false;
    }

    /**
     * Gets the pitch acceleration of the device
     * @return Pitch acceleration (m/s^2) of the device
     */
    public float getPitch()
    {
        return pitch;
    }

    @Override
    public void run()
    {
        while(running)
        {
            pitch = orientationData.getPitch();
        }

        Log.e("E", "Accel thread stopping");
    }
}
