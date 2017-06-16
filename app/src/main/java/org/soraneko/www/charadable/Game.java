package org.soraneko.www.charadable;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eric on 6/16/2017.
 * https://developer.android.com/reference/android/os/CountDownTimer.html
 */

public class Game extends AppCompatActivity
{

    private final int gameInterval = 60000; // 60 seconds
    private final int introInterval = 5000; //5 Seconds
    private ArrayList<String> cards;
    TextView timer;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);


        timer = (TextView) findViewById(R.id.timer);
        text = (TextView) findViewById(R.id.timer);

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
                text.setText("Set device into horizontal position");
            }

            public void onFinish() {
                text.setText("Game starting");
            }
        }.start();

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                text.setText("Game ended");
            }
        }.start();

    }


    private void loadDeck(Deck deck)
    {
        cards = deck.getCardDeck();
    }


}
