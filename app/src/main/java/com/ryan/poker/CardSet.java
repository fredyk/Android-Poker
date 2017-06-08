package com.ryan.poker;

import java.util.ArrayList;
import java.util.Collections;

public class CardSet {

    public ArrayList<Card> cards = new ArrayList();

    public CardSet() {}

    public void add(Card x) {
        cards.add(x);
    }

    public Card getFirst() {
        return cards.get(0);
    }

    public void remove(Card x) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getSuit().equals(x.getSuit()) && cards.get(i).getValue().equals(x.getValue())) {
                cards.remove(i);
            }
        }
    }

    public int size() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void clear() {
        cards.clear();
    }

    public String print() {
        String output = "";
        for (int i = 0; i < cards.size(); i++) {
            output += cards.get(i).print();
            output += " ";
        }
        return output;
    }

    public CardSet copy() {
        CardSet output = new CardSet();
        for(int i = 0; i < cards.size(); i++)
            output.add(cards.get(i));
        return output;
    }
}
