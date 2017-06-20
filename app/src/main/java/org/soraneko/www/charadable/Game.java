package org.soraneko.www.charadable;

import android.util.Log;
import android.widget.TextView;

import java.lang.invoke.VolatileCallSite;
import java.util.ArrayList;

/**
 * Created by Eric on 6/19/2017.
 */

public class Game implements Runnable
{

    private TextView cardTextView;
    private ArrayList<String> cardDeck;
    private ArrayList<String> skippedCardsDeck;
    private RunnableAccel runnableAccel;
    private String currentCard;
    private volatile boolean running = true;
    private Thread t;
    private int numberOfCardsGuessedRight;
    private int numberOfCards;

    public Game(TextView cardView, ArrayList<String> deck, ArrayList<String> skippedCards, RunnableAccel ra)
    {
        cardTextView = cardView;
        cardDeck = deck;
        skippedCardsDeck = skippedCards;
        runnableAccel = ra;
        numberOfCards = cardDeck.size();
        currentCard = cardDeck.get(0);
        skippedCardsDeck = new ArrayList<String>();
        Log.e("G", "Game thread object created");
    }

    public void start()
    {
        Log.e("G", "Starting a Game thread");
        if (t == null)
        {
            t = new Thread(this, "GameThread");
            t.start();
        }
    }

    public void stop()
    {
        running = false;
    }

    public int getNumberOfCardsGuessedRight()
    {
        return numberOfCardsGuessedRight;
    }

    public int getNumberOfSkippedCards()
    {
        return skippedCardsDeck.size();
    }

    public String getCurrentCard()
    {
        return currentCard;
    }

    public ArrayList<String> getSkippedCardsDeck()
    {
        return skippedCardsDeck;
    }

    private void next()
    {
        if (cardDeck.size() > 0)
        {
            if (skippedCardsDeck.contains(currentCard))
            {
                int index = skippedCardsDeck.indexOf(currentCard);
                skippedCardsDeck.remove(index);
            }
            cardDeck.remove(0);
            numberOfCardsGuessedRight++;

            try
            {
                currentCard = cardDeck.get(0);
            } catch (IndexOutOfBoundsException ex) {
                Log.e("G", "No more cards in the deck!");
            }

            // Gives time for the user to reposition the device
            try
            {
                t.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void skip()
    {
        if (!skippedCardsDeck.contains(currentCard))
        {
            skippedCardsDeck.add(currentCard);
        }
        cardDeck.add(cardDeck.remove(0));
        currentCard = cardDeck.get(0);
    }

    @Override
    public void run()
    {
        while(running)
        {
            float pitch = runnableAccel.getPitch();
            // If the pitch is less than -6.94 m/s^2, the person guessed the card right
            if (pitch < -6.94)
            {
                Log.e("G", "Person says he/she got it right");
                next();
            }
            //If the pitch is greater than 6.94 m/s^S, the person is skipping the card
            if (pitch > 6.94)
            {
                Log.e("G", "Person says he/she skips" + " Pitch: " + Float.toString(pitch));
                skip();
            }

        }

        Log.e("G", "Accel thread stopping");
    }
}
