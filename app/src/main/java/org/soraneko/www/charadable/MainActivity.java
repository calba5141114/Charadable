package org.soraneko.www.charadable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button createDeckButton;
    Button loadGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDeckButton = (Button) findViewById(R.id.createDeck);
        loadGameButton = (Button) findViewById(R.id.loadGame);
        loadGameHandler();
        createDeckHandler();
    }
    //loads list of games
    public void loadGameHandler (){
        loadGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent loadgameintent = new Intent(MainActivity.this, loadGamelist.class);
                startActivity(loadgameintent);
            }
        });
    }


    //needs functions to work properly
    public void createDeckHandler (){
        createDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loaddeckintent = new Intent(MainActivity.this, createDeck.class);
                startActivity(loaddeckintent);
            }
        });

    }

}
