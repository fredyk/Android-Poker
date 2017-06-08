package com.ryan.poker;

public class Card {

    public String suit;
    public String value;

    public Card(String cardValue, String cardSuit) {
        suit = cardSuit;
        value = cardValue;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public String print() {
        String output;
        output = value + suit;
        return output;
    }
}
