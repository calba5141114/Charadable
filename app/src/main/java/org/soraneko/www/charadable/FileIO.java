package org.soraneko.www.charadable;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import  java.io.ObjectOutputStream;

/**
 * Created by Carlos on 6/13/2017.
 */

/**
 * Class dedicated to reading and writing Deck files on the device
 */
public class FileIO {

    /**
     * Default constructor of the class
     */
    public FileIO()
    {

    }

    /**
     * Used to save deck objects as a file on the device
     * @param deck Initialized deck object
     * @param title Name for the file and deck
     * @param filepath Path to which the file will be written too
     */
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

    /**
     * Reading deck files from a File object
     * @param file File object representing the deck object
     * @return A Deck object
     */
    public Deck readDeck(File file)
    {
        Deck temp = null;
        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try
        {
            fin = new FileInputStream(file);
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
