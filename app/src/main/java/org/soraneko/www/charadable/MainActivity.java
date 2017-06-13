package org.soraneko.www.charadable;

import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button createDeck = (Button)findViewById(R.id.createDeck);
    Button loadGame = (Button) findViewById(R.id.loadGame);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadGameHandler (){
        loadGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent loadgameintent = new Intent(MainActivity.this, loadGamelist.class);
                startActivity(loadgameintent);
            }
        });
    }


    //needs functions to work properly
    public void createDeckHandler (){
        createDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }







}
