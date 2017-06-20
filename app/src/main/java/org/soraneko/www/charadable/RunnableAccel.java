package org.soraneko.www.charadable;

import android.util.Log;

/**
 * Created by Eric on 6/18/2017.
 * https://www.tutorialspoint.com/java/java_multithreading.htm
 */

public class RunnableAccel implements Runnable
{

    private OrientationData orientationData;
    private volatile boolean running = true;
    private float pitch;
    private Thread t;

    public RunnableAccel(OrientationData orienData)
    {
        orientationData = orienData;
        Log.e("E", "Accelerameter Thread Object Created");
    }

    public void start()
    {
        Log.e("E", "Starting an accelerameter thread");
        if (t == null)
        {
            t = new Thread(this, "AccelThread");
            t.start();
        }

    }

    public void stop()
    {
        running = false;
    }

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
