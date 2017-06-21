package com.ryan.poker;

/**
 * Created by Ryan on 6/11/2017.
 */

public class Functions {
    public static CardSet combine(CardSet hand, CardSet river) {
        CardSet out = new CardSet();
        CardSet a = hand.copy();
        CardSet b = river.copy();
        int aSize = a.size();
        int bSize = b.size();
        for (int i = 0; i < aSize; i++) {
            out.add(a.getFirst());
            a.remove(a.getFirst());
        }
        for (int i = 0; i < bSize; i++) {
            out.add(b.getFirst());
            b.remove(b.getFirst());
        }
        return out;
    }

    public static CardSet populate(CardSet deck) { //creates full deck of cards in order
        CardSet fullDeck = new CardSet();
        String suits[] = { "C", "D", "H", "S" };
        String values[] = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
        for (int j = 0; j < 13; j++) {
            for (int i = 0; i < 4; i++) {
                fullDeck.add(new Card(values[j], suits[i]));
            }
        }
        return fullDeck;
    }
}
