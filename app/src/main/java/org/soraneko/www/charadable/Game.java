package org.soraneko.www.charadable;

import android.util.Log;
import android.widget.TextView;

import java.lang.invoke.VolatileCallSite;
import java.util.ArrayList;

/**
 * Created by Eric on 6/19/2017.
 *
 * This is where the game runs.
 * Using multithreading, it keeps track the pitch
 * of the device and conducts the appropriate actions
 * for the game based on the pitch.
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

    /**
     * Constructor of a Game thread
     * @param cardView TextView object that will be showing the cards
     * @param deck ArrayList of strings that represent the card deck
     * @param skippedCards ArrayList to put in the skipped cards
     * @param ra Reference to a RunnableAccel thread object
     */
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

    /**
     * Starts the Game thread
     */
    public void start()
    {
        Log.e("G", "Starting a Game thread");
        if (t == null)
        {
            t = new Thread(this, "GameThread");
            t.start();
        }
    }

    /**
     * Stops Game thread
     */
    public void stop()
    {
        running = false;
    }

    /**
     * Gets the number of cards guessed right
     * @return Number of skipped cards
     */
    public int getNumberOfCardsGuessedRight()
    {
        return numberOfCardsGuessedRight;
    }

    /**
     * Gets number of skipped cards
     * @return Number of skipped cards
     */
    public int getNumberOfSkippedCards()
    {
        return skippedCardsDeck.size();
    }

    /**
     * Gets the current card being played
     * @return String with the current card
     */
    public String getCurrentCard()
    {
        return currentCard;
    }

    /**
     * Gets the deck of skipped cards
     * @return ArrayList of skipped cards
     */
    public ArrayList<String> getSkippedCardsDeck()
    {
        return skippedCardsDeck;
    }

    /**
     * Proceeds with the next card in the deck
     */
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

    /**
     * Handles actions required when the user skips a card
     */
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
