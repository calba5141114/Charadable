package org.soraneko.www.charadable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Manages the creation of card Decks based on the user input
 */
public class createDeck extends AppCompatActivity {

    EditText wordInput;
    Button newCard;
    Button saveCards;
    EditText createTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deck);
        wordInput = (EditText) findViewById(R.id.wordCreate);
        newCard = (Button) findViewById(R.id.newCard);
        saveCards = (Button) findViewById(R.id.savecards);
        createTitle = (EditText) findViewById(R.id.titleCreate);
        newCardHandler();
        saveCardsHandler();
    }

    ArrayList<String> Cards = new ArrayList<String>();

    /**
     * Handles the setting of an OnClickListener for the newCard button
     */
    public void newCardHandler () {
        newCard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //does action
                if (wordInput.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "You need to put in something", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Cards.add(wordInput.getText().toString());
                    wordInput.getText().clear();
                    Toast.makeText(getApplicationContext(), "Card added", Toast.LENGTH_SHORT).show();
                }
            }
        });}

    /**
     * Handles the setting of an OnClickListener for the saveCards button
     */
    public void saveCardsHandler (){
        saveCards.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //does action
                String name = createTitle.getText().toString();
                if (Cards.size() == 0)
                {
                    Toast.makeText(getApplicationContext(), "You need to make at least one card", Toast.LENGTH_SHORT).show();
                }
                else if (name.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "You need a title", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Deck cardDeck = new Deck(Cards, name);
                    FileIO temp = new FileIO();

                    temp.serializeDeck(cardDeck, name, getFilesDir().toString());
                    createTitle.getText().clear();
                    Cards.clear();
                    Toast.makeText(getApplicationContext(), "Deck created!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}