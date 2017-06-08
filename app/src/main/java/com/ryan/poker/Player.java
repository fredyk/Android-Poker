package com.ryan.poker;

import java.util.ArrayList;

public class Player implements Comparable<Player> {

    public String name;
    public int money;
    public CardSet hand = new CardSet();
    public int amountBet;
    public String state;
    public ArrayList<Integer> handResults = new ArrayList();

    public Player(String playerName, int playerMoney) {
        name = playerName;
        money = playerMoney;
        amountBet = 0;
        state = "active|nb";
    }

    public void editMoney(int x) {
        money = money + x;
    }

    public void deal(Card x) {
        hand.add(x);
    }

    public void clearHand() {
        hand.clear();
    }

    public void setState(String x) {
        state = x;
    }

    public void setHandResults(ArrayList<Integer> x) {
        handResults = x;
    }

    public void setAmountBet(int x) {
        amountBet = x;
    }

    public int bet(int amount) {
        money -= amount;
        amountBet += amount;
        return amount;
    }

    public CardSet getHand() {
        return hand;
    }

    public ArrayList<Integer> getHandResults() {
        return handResults;
    }

    public String printName() {
        return name;
    }

    String printState() {
        return state;
    }

    public void clearAmountBet() {
        amountBet = 0;
    }

    public void clearHandResults() {
        handResults.clear();
    }

    public int getAmountBet() {
        return amountBet;
    }

    public int printMoney() {
        return money;
    }

    @Override public int compareTo(Player compareTo) {
        int compareValue = compareTo.getAmountBet();
        return this.amountBet - compareValue;
    }
}