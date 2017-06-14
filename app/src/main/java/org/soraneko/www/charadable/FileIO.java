package org.soraneko.www.charadable;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import  java.io.ObjectOutputStream;

/**
 * Created by Carlos on 6/13/2017.
 */


public class FileIO extends AppCompatActivity {

    public FileIO()
    {

    }

    // Use the save decks
    public void serializeDeck (Deck deck, String title, String filepath){

       FileOutputStream fOut = null;
       ObjectOutputStream oos = null;

        try
        {
            File file = new File(filepath, title + ".lul");
            fOut = new FileOutputStream(file);
            oos = new ObjectOutputStream(fOut);
            oos.writeObject(deck);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {

            if(fOut != null)
            {
                try
                {
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(oos != null)
            {
                try
                {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    // Use to read in a Deck object
    public Deck readDeck(String filepath, String filename)
    {
        Deck temp = null;
        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try
        {
            File file = new File(filepath, filename);
            fin = openFileInput(file.toString());
            ois = new ObjectInputStream(fin);
            temp = (Deck) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fin != null)
            {
                try
                {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if(ois != null)
            {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return temp;
    }
}
