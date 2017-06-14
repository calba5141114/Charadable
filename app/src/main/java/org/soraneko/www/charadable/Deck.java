package org.soraneko.www.charadable;

import java.io.Serializable;
import java.util.ArrayList;



 public class Deck implements Serializable{

    private static long serialVersionUID = 1L;
    private ArrayList <String> cardDeck;
    private String title;

    public Deck()
    {
        cardDeck = null;
        title = null;
    }


    public Deck(ArrayList<String> cards, String title)
    {
        cardDeck = cards;
        this.title = title;
    }


    public ArrayList<String> getCardDeck()
    {
        return cardDeck;
    }

    public String getTitle()
    {
        return title;
    }

}
