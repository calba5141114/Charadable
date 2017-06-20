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
 * + pitch tilts device down (-6.94 -> 45 degrees)
 * - pitch tilts device up (6.94 -> -45 degrees)
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
    private Game gameThread;
    TextView timer;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);

        /**************** Getting TextView components *****************/
        timer = (TextView) findViewById(R.id.timer);
        text = (TextView) findViewById(R.id.text);
        /**************************************************************/

        /**************** SENSOR STUFF *****************/
        SensorManager mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        orientationData = new OrientationData(mSensorManager);
        orientationData.onResume();
        accelThread = new RunnableAccel(orientationData);
        accelThread.start();

        Deck deck = (Deck) getIntent().getExtras().get("deck");
        loadDeck(deck);
        /**********************************************/

        /**************** Creating Game Thread Stuff *****************/
        gameThread = new Game(text, cards, skippedCards, accelThread);

        new CountDownTimer(65000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                if ((millisUntilFinished / 1000) == 60)
                {
                    /** Starts the Game thread **/
                    gameThread.start();
                }
                else if ((millisUntilFinished / 1000) > 60)
                {
                    text.setText("Set device to horizontal position");
                    timer.setText("" + ((millisUntilFinished / 1000) - 60));
                }
                else
                {
                    timer.setText("" + millisUntilFinished / 1000);
                    text.setText(gameThread.getCurrentCard());
                }

                if (gameThread.getNumberOfCardsGuessedRight() == numberOfCards)
                {
                    gameOver();
                    this.cancel();
                }

                Log.e("GM", Integer.toString(gameThread.getNumberOfCardsGuessedRight()));

            }

            public void onFinish()
            {
                gameOver();
            }
        }.start();

    }

    /**
     * Loads up the deck from the Deck object passed in
     * @param deck A initialized deck object with card deck inside
     */
    private void loadDeck(Deck deck)
    {
        cards = deck.getCardDeck();
        numberOfCards = cards.size();
        Collections.shuffle(cards);
    }

    /**
     * Handles things when the game is over
     */
    private void gameOver()
    {
        Intent loadGameOverIntent = new Intent(GameManager.this, GameOver.class);
        numberOfSkippedCards = gameThread.getNumberOfSkippedCards();
        numberOfRightCards = gameThread.getNumberOfCardsGuessedRight();
        skippedCards = gameThread.getSkippedCardsDeck();

        loadGameOverIntent.putExtra("skipped", skippedCards);
        loadGameOverIntent.putExtra("numberOfCards", numberOfCards);
        loadGameOverIntent.putExtra("numberOfSkippedCards", numberOfSkippedCards);
        loadGameOverIntent.putExtra("numberOfRightCards", numberOfRightCards);
        gameThread.stop();
        accelThread.stop();
        orientationData.onPause();
        startActivity(loadGameOverIntent);
    }


}
