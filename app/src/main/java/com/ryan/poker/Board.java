package com.ryan.poker;

public class Board {

    public int pot;
    public int maxBet;
    public CardSet river = new CardSet();
    public boolean devMode;

    public Board() {
        pot = 0;
        maxBet = 0;
        devMode = false;
    }

    public void addToPot(int x) {
        pot += x;
    }

    public void removeFromPot(int x) {
        pot -= x;
    }

    public void setMaxBet(int x) {
        maxBet = x;
    }

    public void addToRiver(Card x) {
        river.add(x);
    }

    public void setDevMode(boolean x){
        devMode = x;
    }

    public boolean getDevMode(){
        return devMode;
    }

    public int getPot() {
        return pot;
    }

    public int getMaxBet() {
        return maxBet;
    }

    public CardSet getRiver() {
        return river;
    }

    public void clearPot() {
        pot = 0;
    }

    public void clearMaxBet() {
        maxBet = 0;
    }

    public void clearRiver() {
        river.clear();
    }
}
