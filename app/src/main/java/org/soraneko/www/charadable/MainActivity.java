package org.soraneko.www.charadable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

/**
 * Main menu of the app
 */
public class MainActivity extends AppCompatActivity {

    Button createDeckButton;
    Button loadGameButton;
    Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDeckButton = (Button) findViewById(R.id.createDeck);
        loadGameButton = (Button) findViewById(R.id.loadGame);
        shareButton = (Button) findViewById(R.id.shareButton);
        loadGameHandler();
        createDeckHandler();
        shareButtonHandler();

    }

    /**
     * Sets an OnClickListener on loadGameButton
     */
    public void loadGameHandler (){
        loadGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent loadgameintent = new Intent(MainActivity.this, loadGamelist.class);
                startActivity(loadgameintent);
            }
        });
    }

    /**
     * Sets an OnClickListener on createDeckButton
     */
    public void createDeckHandler (){
        createDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loaddeckintent = new Intent(MainActivity.this, createDeck.class);
                startActivity(loaddeckintent);
            }
        });
    }

    public void shareButtonHandler()
    {
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "https://github.com/";
                String shareSub = "Try out Charadable!";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share using"));
            }
        });
    }

}
