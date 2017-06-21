package org.soraneko.www.charadable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eric on 6/18/2017.
 *
 * Handles and displays the information from the game in a post-game view
 */

public class GameOver extends AppCompatActivity
{

    private ArrayList<String> skippedCards;
    private int overallScore;
    private int numberOfSkippedCards;
    private int numberOfRightCards;
    ListView skippedCardsListView;
    TextView numberSkippedTextView;
    TextView numberRightTextView;
    TextView noSkippedCardsView;
    TextView overallScoreTextView;
    Button playAgainButton;
    Button mainMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        skippedCards = (ArrayList<String>) getIntent().getExtras().get("skipped");
        overallScore = (int) getIntent().getExtras().get("overallScore");
        numberOfSkippedCards = (int) getIntent().getExtras().get("numberOfSkippedCards");
        numberOfRightCards = (int) getIntent().getExtras().get("numberOfRightCards");

        skippedCardsListView = (ListView)findViewById(R.id.listViewSkipped);
        numberSkippedTextView = (TextView) findViewById(R.id.textNumberSkipped);
        numberRightTextView = (TextView) findViewById(R.id.numberCorrectTextView);
        noSkippedCardsView = (TextView) findViewById(R.id.noSkippedCardsTextView);
        overallScoreTextView = (TextView) findViewById(R.id.overallScoreTextView);
        playAgainButton = (Button) findViewById(R.id.playAgain);
        mainMenuButton = (Button) findViewById(R.id.mainMenu);

        numberSkippedTextView.setText(Integer.toString(numberOfSkippedCards));
        numberRightTextView.setText(Integer.toString(numberOfRightCards));
        overallScoreTextView.setText(Integer.toString(overallScore));

        if (numberOfSkippedCards == 0)
        {
            noSkippedCardsView.setVisibility(View.VISIBLE);
        }
        else
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, skippedCards);
            skippedCardsListView.setAdapter(adapter);
        }

        playAgainButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent loadGameIntent = new Intent(GameOver.this, loadGamelist.class);
                startActivity(loadGameIntent);
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent loadMainMenuIntent = new Intent(GameOver.this, MainActivity.class);
                startActivity(loadMainMenuIntent);
            }
        });

    }

}
