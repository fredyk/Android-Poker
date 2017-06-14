package com.ryan.poker;

/**
 * Created by Ryan on 6/11/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    public static ArrayList<Player> playerSetup(String[] playerNameArray, int[] playerMoneyArray) {
        ArrayList<Player> players = new ArrayList();
        for (int i = 0; i < playerNameArray.length; i++)//Set up vector of players
            players.add(new Player(playerNameArray[i], playerMoneyArray[i]));
        return players;
    }

    public static String callButton(Player currentPlayer, Board currentGame) {
        String output;
        if ((currentGame.getMaxBet() - currentPlayer.getAmountBet()) == 0 || currentPlayer.printMoney() == 0)
            output = "Check";
        else if (currentPlayer.printMoney() < (currentGame.getMaxBet() - currentPlayer.getAmountBet())) //if they do not have enough to fully call, let them go all in
            output = "Call($" + currentPlayer.printMoney() + ")";
        else
            output = "Call($" + (currentGame.getMaxBet() - currentPlayer.getAmountBet()) + ")";
        return output;
    }

    public static void takeTurn(Player currentPlayer, ArrayList<Player> players, Board currentGame, String decision) {
        //system("cls"); //clear console
        String userDecision; //string for user input (ex: fold, bet, etc.)
        System.out.println("It is now your turn " + currentPlayer.printName());
        Scanner reader = new Scanner(System.in);
        for (int i = 0; i < players.size(); i++) { //print out all users name and money
            if (players.get(i).printState().equals("active|nb") || players.get(i).printState().equals("active|b"))
                System.out.print(players.get(i).printName() + " - $" + players.get(i).printMoney() + "   ");
            if (players.get(i).printState().equals("folded"))
                System.out.print(players.get(i).printName() + " (folded)- $" + players.get(i).printMoney() + "   ");
        }
        System.out.println("Pot - $" + currentGame.getPot()); //print out amount in pot
        System.out.println("River: " + currentGame.getRiver().print()); //print out cards in river
        while (true) {
            if ((currentGame.getMaxBet() - currentPlayer.getAmountBet()) == 0 || currentPlayer.printMoney() == 0) {
                System.out.println("The following commands are available: Hand, Fold, Check, Bet");
                System.out.print("> ");
                userDecision = reader.nextLine();
            }
            else if (currentPlayer.printMoney() < (currentGame.getMaxBet() - currentPlayer.getAmountBet())) { //if they do not have enough to fully call, let them go all in
                System.out.println("The following commands are available: Hand, Fold, Call($" + currentPlayer.printMoney() + "), Bet");
                System.out.print("> ");
                userDecision = reader.nextLine();
            }
            else {
                System.out.println("The following commands are available: Hand, Fold, Call($" + (currentGame.getMaxBet() - currentPlayer.getAmountBet()) + "), Bet");
                System.out.print("> ");
                userDecision = reader.nextLine();
            }
            if (userDecision.equals("Hand")) { //show user their hand
                System.out.println(currentPlayer.getHand().print());
            }
            else if (userDecision.equals("Fold")) { //set the users state to fold and end turn
                currentPlayer.setState("folded");
                break;
            }
            else if ((currentGame.getMaxBet() - currentPlayer.getAmountBet()) != 0 && userDecision.equals("Call")) { //bet the amount required and end turn
                if (currentPlayer.printMoney() < (currentGame.getMaxBet() - currentPlayer.getAmountBet())) {
                    currentGame.addToPot(currentPlayer.bet(currentPlayer.printMoney()));
                }
                else //if they have enough money to call then bet that amount
                    currentGame.addToPot(currentPlayer.bet(currentGame.getMaxBet() - currentPlayer.getAmountBet()));
                currentPlayer.setState("active|b");
                break;
            }
            else if (((currentGame.getMaxBet() - currentPlayer.getAmountBet()) == 0 || currentPlayer.printMoney() == 0) && userDecision.equals("Check")) { //set state to have bet and move to next player
                currentPlayer.setState("active|b");
                break;
            }
            else if (userDecision.equals("Bet")) { //request the amount the user wants to bet and then end turn
                int betAmount;
                while (true) {
                    System.out.print("Insert the amount you want to bet: ");
                    betAmount = reader.nextInt();
                    reader.nextLine();
                    if (betAmount <= (currentGame.getMaxBet() - currentPlayer.getAmountBet()) || betAmount > currentPlayer.printMoney())
                        System.out.println("Invalid bet amount.");
                    else
                        break;
                }
                currentGame.addToPot(currentPlayer.bet(betAmount));
                currentGame.setMaxBet(currentPlayer.getAmountBet());
                currentPlayer.setState("active|b");
                for(int i = 0; i < players.size(); i++){
                    if(!currentPlayer.printName().equals(players.get(i).printName()) && !players.get(i).printState().equals("folded"))
                        players.get(i).setState("active|nb");
                }
                break;
            }
            else {
                System.out.println("Invalid input.");
            }
        }
    }

    public static void roundSetup(Board currentGame, ArrayList<Player> players, int blind, int smallBlind, CardSet deck){
        for (int i = 0; i < players.size(); i++) { //deal everyone two cards
            players.get(i).deal(deck.getFirst());
            deck.remove(deck.getFirst());
            players.get(i).deal(deck.getFirst());
            deck.remove(deck.getFirst());
        }
        if (players.get(blind).printMoney() >= smallBlind) //if they have enough to pay small blind
            currentGame.addToPot(players.get(blind).bet(smallBlind)); //pay small blind
        else
            currentGame.addToPot(players.get(blind).bet(players.get(blind).printMoney())); //pay as much as they can
        if (players.get((blind + 1) % players.size()).printMoney() >= (smallBlind * 2)) //if they have enough to pay big blind
            currentGame.addToPot(players.get((blind + 1) % players.size()).bet(smallBlind * 2)); //pay big blind
        else
            currentGame.addToPot(players.get((blind + 1) % players.size()).bet(players.get((blind + 1) % players.size()).printMoney())); //pay as much as they can
        currentGame.setMaxBet(smallBlind * 2);
    }

    public static void iterateRound(Board currentGame, ArrayList<Player> players, int round, CardSet deck) {
        int currentPlayers = 0;
        for (int i = 0; i < players.size(); i++) { //find amount of players still in round
            if (players.get(i).printState().equals("active|nb") || players.get(i).printState().equals("active|b"))
                currentPlayers++;
        }
        int playersDoneBetting = 0;
        for (int i = 0; i < players.size(); i++) { //check if everyone is done betting
            if (players.get(i).printState().equals("active|b")) {
                playersDoneBetting++;
            }
        }
        if (playersDoneBetting == currentPlayers) { //move to second round
            if (round == 1) {
                for (int i = 0; i < 3; i++) { //3 cards into the river on 1st round
                    currentGame.addToRiver(deck.getFirst());
                    deck.remove(deck.getFirst());
                }
                for (int j = 0; j < players.size(); j++) { //reset players bet status for next round of betting
                    if (!players.get(j).printState().equals("folded"))
                        players.get(j).setState("active|nb");
                }
                round++;
            }
            else if (round == 2 || round == 3) { //1 card into the river on 2nd and 3rd rounds
                currentGame.addToRiver(deck.getFirst());
                deck.remove(deck.getFirst());
                for (int i = 0; i < players.size(); i++) { //reset players bet status for next round of betting
                    if (!players.get(i).printState().equals("folded"))
                        players.get(i).setState("active|nb");
                }
                round++;
            }
            else if (round == 4) { //find winner at end of round
                ArrayList<Player> finalists = new ArrayList();
                ArrayList<Player> winners;
                for (int i = 0; i < players.size(); i++) {
                    if (!players.get(i).printState().equals("folded")) {
                        CardSet total = Functions.combine(players.get(i).getHand(), currentGame.getRiver());
                        players.get(i).setHandResults(FindBestHand.findBestHand(total));
                        finalists.add(players.get(i));
                    }
                }
                while (currentGame.getPot() > 0) {
                    int numFinalists = finalists.size();
                    winners = FindWinner.findWinner(finalists);
                    if (winners.size() == 1) {
                        for (int i = 0; i < players.size(); i++) {
                            if (players.get(i).printName().equals(winners.get(0).printName())) {
                                if (players.get(i).getAmountBet() == currentGame.getMaxBet()) {
                                    players.get(i).editMoney(currentGame.getPot());
                                    System.out.println(players.get(i).printName() + " has won the pot!");
                                    currentGame.clearPot();
                                }
                                else {
                                    String removeName = players.get(i).printName();
                                    int sidePotAmount = 0;
                                    for (int j = 0; j < players.size(); j++) {
                                        if (players.get(j).getAmountBet() >= players.get(i).getAmountBet()) {
                                            players.get(i).editMoney(players.get(i).getAmountBet());
                                            currentGame.removeFromPot(players.get(i).getAmountBet());
                                            sidePotAmount += players.get(i).getAmountBet();
                                            players.get(j).setAmountBet(players.get(j).getAmountBet() - players.get(i).getAmountBet());
                                        }
                                        else{
                                            players.get(i).editMoney(players.get(j).getAmountBet());
                                            currentGame.removeFromPot(players.get(j).getAmountBet());
                                            sidePotAmount += players.get(j).getAmountBet();
                                            players.get(j).setAmountBet(players.get(j).getAmountBet() - players.get(j).getAmountBet());
                                        }
                                    }
                                    System.out.println(players.get(i).printName() + " has won a side pot for $" + sidePotAmount + "!");
                                    for (int j = 0; j < finalists.size(); j++) {
                                        if (finalists.get(j).printName().equals(removeName)) {
                                            finalists.remove(j);
                                            j = 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        Collections.sort(winners);
                        int winnersSize = winners.size();
                        int potSplit = currentGame.getPot() / winnersSize;
                        int sidePotAmount = 0;
                        for (int i = 0; i < winnersSize; i++) {
                            for (int j = 0; j < players.size(); j++) {
                                if (players.get(j).printName().equals(winners.get(i).printName())) {
                                    if (players.get(j).getAmountBet() == currentGame.getMaxBet()) { //if theyve paid the potSplit amount, they get their money back
                                        sidePotAmount = potSplit;
                                        players.get(j).editMoney(potSplit);
                                        System.out.print(players.get(j).printName() + " ");
                                        currentGame.clearPot();
                                    }
                                    else {
                                        String removeName = players.get(j).printName();
                                        System.out.print(removeName + " ");
                                        for (int k = 0; k < players.size(); k++) {
                                            if (players.get(k).getAmountBet() >= players.get(j).getAmountBet()) {
                                                sidePotAmount += players.get(j).getAmountBet();
                                                players.get(k).setAmountBet(players.get(k).getAmountBet() - players.get(j).getAmountBet());
                                            }
                                            else{
                                                sidePotAmount += players.get(k).getAmountBet();
                                                players.get(k).setAmountBet(players.get(k).getAmountBet() - players.get(k).getAmountBet());
                                            }
                                        }
                                        int distributeAmount = sidePotAmount / (winnersSize - i);
                                        for (int k = i; k < winnersSize; k++) {
                                            for (int l = 0; l < players.size(); l++) {
                                                if (players.get(l).printName().equals(winners.get(k).printName())) {
                                                    players.get(l).editMoney(distributeAmount);
                                                    currentGame.removeFromPot(distributeAmount);
                                                }
                                            }
                                        }
                                        for (int k = 0; k < finalists.size(); k++) {
                                            if (finalists.get(k).printName().equals(removeName)) {
                                                finalists.remove(k);
                                                k = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        System.out.println(" have split the pot for $" + sidePotAmount + " each!");
                    }
                    if (finalists.size() == 0)
                        currentGame.clearPot();
                }
                for (int i = 0; i < players.size(); i++) { //print out all users name and money
                    if (players.get(i).printState().equals("active|nb") || players.get(i).printState().equals("active|b"))
                        System.out.print(players.get(i).printName() + " - $" + players.get(i).printMoney() + "   ");
                    if (players.get(i).printState().equals("folded"))
                        System.out.print(players.get(i).printName() + " (folded)- $" + players.get(i).printMoney() + "   ");
                }
                System.out.println("\n" + "River: " + currentGame.getRiver().print()); //print out cards in river

                for (int i = 0; i < players.size(); i++) {
                    if (!players.get(i).printState().equals("folded")) {
                        System.out.print(players.get(i).printName() + " - ");
                        if (players.get(i).getHandResults().get(0) == 9)
                            System.out.print("Straight flush");
                        else if (players.get(i).getHandResults().get(0) == 8)
                            System.out.print("Four of a kind");
                        else if (players.get(i).getHandResults().get(0) == 7)
                            System.out.print("Full house");
                        else if (players.get(i).getHandResults().get(0) == 6)
                            System.out.print("Flush");
                        else if (players.get(i).getHandResults().get(0) == 5)
                            System.out.print("Straight");
                        else if (players.get(i).getHandResults().get(0) == 4)
                            System.out.print("Three of a kind");
                        else if (players.get(i).getHandResults().get(0) == 3)
                            System.out.print("Two pair");
                        else if (players.get(i).getHandResults().get(0) == 2)
                            System.out.print("One pair");
                        else if (players.get(i).getHandResults().get(0) == 1)
                            System.out.print("High card");
                        System.out.println(" ( " + players.get(i).getHand().print() + ")");
                    }
                }
            }
        }
    }
}