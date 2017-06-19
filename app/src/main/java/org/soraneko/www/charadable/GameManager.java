package org.soraneko.www.charadable;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Eric on 6/16/2017.
 * https://developer.android.com/reference/android/os/CountDownTimer.html
 * https://developer.android.com/reference/android/hardware/SensorEvent.html
 * https://www.youtube.com/watch?v=MwH0z1HIxog
 * https://www.youtube.com/watch?v=vqvJn4G3NMM
 *
 * For rotation focus on the pitch of the phone
 * No matter if Yaw is positive or negative, pitch has same effect
 * + pitch tilts device down
 * - pitch tilts device up
 */

public class GameManager extends AppCompatActivity
{

    private ArrayList<String> cards;
    private ArrayList<String> skippedCards;
    private int numberOfCards;
    private int numberOfSkippedCards;
    private int numberOfRightCards;
    private OrientationData orientationData;
    private RunnableAccel accelThread;
    TextView timer;
    TextView text;
    Sensor sensor;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);

        timer = (TextView) findViewById(R.id.timer);
        text = (TextView) findViewById(R.id.timer);

        SensorManager mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        orientationData = new OrientationData(mSensorManager);
        orientationData.onResume();
        accelThread = new RunnableAccel(orientationData);
        accelThread.start();

        Deck deck = (Deck) getIntent().getExtras().get("deck");
        loadDeck(deck);

        new CountDownTimer(5000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                timer.setText("" + millisUntilFinished / 1000);
                text.setText("Set device into horizontal position");
            }

            public void onFinish()
            {
                text.setText("GameManager starting");
            }
        }.start();

        new CountDownTimer(60000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish()
            {
                text.setText("GameManager ended");
                gameOver();
            }
        }.start();

    }

    private void loadDeck(Deck deck)
    {
        cards = deck.getCardDeck();
        numberOfCards = cards.size();
        Collections.shuffle(cards);
    }

    private void gameOver()
    {
        Intent loadGameOverIntent = new Intent(GameManager.this, GameOver.class);
        loadGameOverIntent.putExtra("skipped", skippedCards);
        loadGameOverIntent.putExtra("numberOfCards", numberOfCards);
        loadGameOverIntent.putExtra("numberOfSkippedCards", numberOfSkippedCards);
        loadGameOverIntent.putExtra("numberOfRightCards", numberOfRightCards);
        accelThread.stop();
        orientationData.onPause();
        startActivity(loadGameOverIntent);
    }


}
