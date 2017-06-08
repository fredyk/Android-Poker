package com.ryan.poker;

import java.util.ArrayList;

public class FindWinner {
    public static Player compare(Player a, Player b) {
        Player c = new Player("Tie", 0);
        if (a.getHandResults().get(0) > b.getHandResults().get(0))
            return a;
        else if (a.getHandResults().get(0) < b.getHandResults().get(0))
            return b;
        else {
            if (a.getHandResults().get(1) > b.getHandResults().get(1)) //Tiebreaker 1
                return a;
            else if (a.getHandResults().get(1) < b.getHandResults().get(1))
                return b;
            else {
                if (a.getHandResults().size() == 2)
                    return c;
                else if (a.getHandResults().get(2) > b.getHandResults().get(2)) //Tiebreaker 2
                    return a;
                else if (a.getHandResults().get(2) < b.getHandResults().get(2))
                    return b;
                else {
                    if (a.getHandResults().size() == 3)
                        return c;
                    else if (a.getHandResults().get(3) > b.getHandResults().get(3)) //Tiebreaker 3
                        return a;
                    else if (a.getHandResults().get(3) < b.getHandResults().get(3))
                        return b;
                    else {
                        if (a.getHandResults().size() == 4)
                            return c;
                        else if (a.getHandResults().get(4) > b.getHandResults().get(4)) //Tiebreaker 4
                            return a;
                        else if (a.getHandResults().get(4) < b.getHandResults().get(4))
                            return b;
                        else {
                            if (a.getHandResults().size() == 5)
                                return c;
                            else if (a.getHandResults().get(5) > b.getHandResults().get(5)) //Tiebreaker 5
                                return a;
                            else if (a.getHandResults().get(5) < b.getHandResults().get(5))
                                return b;
                            else
                                return c;
                        }
                    }
                }
            }
        }
    }

    public static ArrayList<Player> findWinner(ArrayList<Player> Players) {
        if (Players.size() == 1)
            return Players;
        Player tie = new Player("Tie", 0);
        ArrayList<Player> output = new ArrayList();
        boolean Player0tie, Player1tie;
        if (Players.size() == 2) { //final iteration
            Player winner = compare(Players.get(0), Players.get(1));
            if (winner.printName().equals(Players.get(0).printName())) { //if winner of last comparison is 1st
                Player0tie = false;
                for (int i = 0; i < output.size(); i++) {
                    if (output.get(1).printName().equals(Players.get(0).printName()))
                        Player0tie = true;
                }
                if (Player0tie == true) //if they are in the tied list
                    return output;
                else { //if they are not in the tied list
                    output.clear();
                    output.add(Players.get(0));
                    return output;
                }
            }
            else if (winner.printName().equals(Players.get(1).printName())) { //if winner of last comparison is 2nd
                Player1tie = false;
                for (int i = 0; i < output.size(); i++) {
                    if (output.get(1).printName().equals(Players.get(1).printName()))
                        Player1tie = true;
                }
                if (Player1tie == true) //if they are in the tied list
                    return output;
                else { //if they are not in the tied list
                    output.clear();
                    output.add(Players.get(1));
                    return output;
                }
            }
            else { //if the last comparison is a tie
                Player0tie = false;
                for (int i = 0; i < output.size(); i++) {
                    if (output.get(1).printName().equals(Players.get(0).printName()))
                        Player0tie = true;
                }
                Player1tie = false;
                for (int i = 0; i < output.size(); i++) {
                    if (output.get(1).printName().equals(Players.get(1).printName()))
                        Player1tie = true;
                }
                if (Player0tie == true && Player1tie == false) //if the first is in the tied list but the second isnt
                    output.add(Players.get(1));
                else if (Player0tie == false && Player1tie == true) //if the first is not in the tied list but the second is
                    output.add(Players.get(0));
                else if (Player0tie == false && Player1tie == false) { //if neither are in the list
                    output.clear();
                    output.add(Players.get(0));
                    output.add(Players.get(1));
                }
                return output;
            }
        }
        else {
            Player winner = compare(Players.get(0), Players.get(1));
            if (winner.printName().equals(Players.get(0).printName())) {
                Player0tie = false;
                for (int i = 0; i < output.size(); i++) {
                    if (output.get(1).printName().equals(Players.get(0).printName()))
                        Player0tie = true;
                }
                if (Player0tie == true) { //if the winner of comparison is in tied list, erase other and move on
                    Players.remove(1);
                    return findWinner(Players);
                }
                else { //if the winner of comparison is not in tied list, clear tied list and move on
                    output.clear();
                    Players.remove(1);
                    return findWinner(Players);
                }
            }
            else if (winner.printName().equals(Players.get(1).printName())) {
                Player1tie = false;
                for (int i = 0; i < output.size(); i++) {
                    if (output.get(1).printName().equals(Players.get(1).printName()))
                        Player1tie = true;
                }
                if (Player1tie == true) { //if the winner of comparison is in tied list, erase other and move on
                    Players.remove(0);
                    return findWinner(Players);
                }
                else { //if the winner of comparison is not in tied list, clear tied list and move on
                    output.clear();
                    Players.remove(0);
                    return findWinner(Players);
                }
            }
            else { //if comparison is a tie
                Player0tie = false;
                for (int i = 0; i < output.size(); i++) {
                    if (output.get(1).printName().equals(Players.get(0).printName()))
                        Player0tie = true;
                }
                Player1tie = false;
                for (int i = 0; i < output.size(); i++) {
                    if (output.get(1).printName().equals(Players.get(1).printName()))
                        Player1tie = true;
                }
                if (Player0tie == true && Player1tie == false) //if the first is in the tied list but the second isnt
                    output.add(Players.get(1));
                else if (Player0tie == false && Player1tie == true) //if the first is not in the tied list but the second is
                    output.add(Players.get(0));
                else if (Player0tie == false && Player1tie == false) { //if neither are in the list
                    output.clear();
                    output.add(Players.get(0));
                    output.add(Players.get(1));
                }
                Players.remove(0);
                return findWinner(Players);
            }
        }
    }
}
