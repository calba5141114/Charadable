package org.soraneko.www.charadable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class loadGamelist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_gamelist);

        final ListView listView = (ListView) findViewById(R.id.deckList);

        ArrayList<File> decks = getListFiles(new File(getFilesDir().toString()));
        String[] namesOfFiles = new String[decks.size()];
        for (int i = 0; i < namesOfFiles.length; i++)
        {
            String title = decks.get(i).getName();
            namesOfFiles[i] = title.substring(0, title.lastIndexOf("."));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namesOfFiles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                ArrayList<File> decks = getListFiles(new File(getFilesDir().toString()));
                Intent loadGameIntent = new Intent(loadGamelist.this, GameManager.class);
                FileIO tempIO = new FileIO();

                Deck deckToLoad = tempIO.readDeck(decks.get(position));
                loadGameIntent.putExtra("deck", deckToLoad);
                startActivity(loadGameIntent);

            }
        });

    }

    private ArrayList<File> getListFiles(File dir)
    {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = dir.listFiles();
        for (File file : files)
        {
            if (file.isDirectory())
            {
                inFiles.addAll(getListFiles(file));
            }
            else
            {
                if(file.getName().endsWith(".lul"))
                {
                    inFiles.add(file);
                }
            }
        }

        return inFiles;
    }

}
