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
    private Thread t;

    public RunnableAccel(OrientationData orienData)
    {
        orientationData = orienData;
        Log.d("I", "Accelerameter Thread Created");
    }

    public void start()
    {
        Log.d("I", "Starting an accelerameter thread");
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

    @Override
    public void run()
    {
        while(running)
        {
            Log.d("I", "Accel thread still running");
        }

        Log.d("I", "Accel thread stopping");
    }
}
