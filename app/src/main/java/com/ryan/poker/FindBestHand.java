package com.ryan.poker;

import java.util.ArrayList;
import java.util.Arrays;

public class FindBestHand {
    public static ArrayList<Integer> whereExists(int start, int end, Integer[] findIn) {
        ArrayList<Integer> output = new ArrayList();
        for (int i = start; i < end + 1; i++) {
            if (findIn[i] > 0) {
                output.add(i);
            }
        }
        return output;
    }

    public static ArrayList<Integer> isStraight(ArrayList<Integer> positions) {
        ArrayList<Integer> output = new ArrayList();
        if (positions.size() == 5) {
            if (positions.get(0) + 1 == positions.get(1) && positions.get(1) + 1 == positions.get(2) && positions.get(2) + 1 == positions.get(3) && positions.get(3) + 1 == positions.get(4)) {
                output.add(1);
                output.add(positions.get(4));
                output.add(positions.get(3));
                output.add(positions.get(2));
                output.add(positions.get(1));
                output.add(positions.get(0));
                return output;
            }
            else {
                output.add(0);
                return output;
            }
        }
        else if (positions.size() == 6) {
            if (positions.get(0) + 1 == positions.get(1) && positions.get(1) + 1 == positions.get(2) && positions.get(2) + 1 == positions.get(3) && positions.get(3) + 1 == positions.get(4)){
                output.add(1);
                output.add(positions.get(4));
                output.add(positions.get(3));
                output.add(positions.get(2));
                output.add(positions.get(1));
                output.add(positions.get(0));
                return output;
            }
            else if (positions.get(1) + 1 == positions.get(2) && positions.get(2) + 1 == positions.get(3) && positions.get(3) + 1 == positions.get(4) && positions.get(4) + 1 == positions.get(5)){
                output.add(1);
                output.add(positions.get(5));
                output.add(positions.get(4));
                output.add(positions.get(3));
                output.add(positions.get(2));
                output.add(positions.get(1));
                return output;
            }
            else {
                output.add(0);
                return output;
            }
        }
        else if (positions.size() == 7) {
            if (positions.get(0) + 1 == positions.get(1) && positions.get(1) + 1 == positions.get(2) && positions.get(2) + 1 == positions.get(3) && positions.get(3) + 1 == positions.get(4)){
                output.add(1);
                output.add(positions.get(4));
                output.add(positions.get(3));
                output.add(positions.get(2));
                output.add(positions.get(1));
                output.add(positions.get(0));
                return output;
            }
            else if (positions.get(1) + 1 == positions.get(2) && positions.get(2) + 1 == positions.get(3) && positions.get(3) + 1 == positions.get(4) && positions.get(4) + 1 == positions.get(5)){
                output.add(1);
                output.add(positions.get(5));
                output.add(positions.get(4));
                output.add(positions.get(3));
                output.add(positions.get(2));
                output.add(positions.get(1));
                return output;
            }
            else if (positions.get(2) + 1 == positions.get(3) && positions.get(3) + 1 == positions.get(4) && positions.get(4) + 1 == positions.get(5) && positions.get(5) + 1 == positions.get(6)){
                output.add(1);
                output.add(positions.get(6));
                output.add(positions.get(5));
                output.add(positions.get(4));
                output.add(positions.get(3));
                output.add(positions.get(2));
                return output;
            }
            else {
                output.add(0);
                return output;
            }
        }
        else if (positions.size() == 8) {
            if (positions.get(0) + 1 == positions.get(1) && positions.get(1) + 1 == positions.get(2) && positions.get(2) + 1 == positions.get(3) && positions.get(3) + 1 == positions.get(4)){
                output.add(1);
                output.add(positions.get(4));
                output.add(positions.get(3));
                output.add(positions.get(2));
                output.add(positions.get(1));
                output.add(positions.get(0));
                return output;
            }
            else if (positions.get(3) + 1 == positions.get(4) && positions.get(4) + 1 == positions.get(5) && positions.get(5) + 1 == positions.get(6) && positions.get(6) + 1 == positions.get(7)){
                output.add(1);
                output.add(positions.get(7));
                output.add(positions.get(6));
                output.add(positions.get(5));
                output.add(positions.get(4));
                output.add(positions.get(3));
                return output;
            }
            else {
                output.add(0);
                return output;
            }
        }
        else{
            output.add(0);
            return output;
        }
    }

    public static ArrayList<Integer> flushHighCards(CardSet limitSuit, String s) {
        ArrayList<Integer> output = new ArrayList();
        CardSet cards = new CardSet();
        int limitSuitSize = limitSuit.size();
        for (int i = 0; i < limitSuitSize; i++) {
            if (limitSuit.getFirst().getSuit().equals(s))
                cards.add(limitSuit.getFirst());
            limitSuit.remove(limitSuit.getFirst());
        }
        int cardSize = cards.size();
        Integer[] countByValue = new Integer[14]; //array of values
        Arrays.fill(countByValue, 0);
        for (int i = 0; i < cardSize; i++) { //count amount of cards with each value
            if (cards.getFirst().getValue().equals("2")) {
                countByValue[1]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("3")) {
                countByValue[2]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("4")) {
                countByValue[3]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("5")) {
                countByValue[4]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("6")) {
                countByValue[5]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("7")) {
                countByValue[6]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("8")) {
                countByValue[7]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("9")) {
                countByValue[8]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("10")) {
                countByValue[9]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("J")) {
                countByValue[10]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("Q")) {
                countByValue[11]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("K")) {
                countByValue[12]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("A")) {
                countByValue[13]++;
                countByValue[0]++;
                cards.remove(cards.getFirst());
            }
        }
        for (int i = 13; i >= 0; i--) { //add value of highest card in suit
            if (countByValue[i] != 0) {
                output.add(i);
                break;
            }
        }
        for (int i = 13; i >= 0; i--) { //add value of next highest card in suit
            if (countByValue[i] != 0 && i != output.get(0)) {
                output.add(i);
                break;
            }
        }
        for (int i = 13; i >= 0; i--) { //add value of next highest card in suit
            if (countByValue[i] != 0 && i != output.get(0) && i != output.get(1)) {
                output.add(i);
                break;
            }
        }
        for (int i = 13; i >= 0; i--) { //add value of next highest card in suit
            if (countByValue[i] != 0 && i != output.get(0) && i != output.get(1) && i != output.get(2)) {
                output.add(i);
                break;
            }
        }
        for (int i = 13; i >= 0; i--) { //add value of next highest card in suit
            if (countByValue[i] != 0 && i != output.get(0) && i != output.get(1) && i != output.get(2) && i != output.get(3)) {
                output.add(i);
                break;
            }
        }
        return output;
    }

    public static ArrayList<Integer> findBestHand(CardSet cards) {
        CardSet temp = cards.copy(); //copy so that we can check for suit after value
        ArrayList<Integer> output = new ArrayList(); //vector used to put output data in
        int cardSize = cards.size();
        Integer[] countByValue = new Integer[14]; //array of values
        Arrays.fill(countByValue, 0);
        Integer[] countBySuit = new Integer[4]; //array of suits
        Arrays.fill(countBySuit, 0);
        for (int i = 0; i < cardSize; i++) { //count amount of cards with each value
            if (cards.getFirst().getValue().equals("2")) {
                countByValue[1]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("3")) {
                countByValue[2]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("4")) {
                countByValue[3]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("5")) {
                countByValue[4]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("6")) {
                countByValue[5]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("7")) {
                countByValue[6]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("8")) {
                countByValue[7]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("9")) {
                countByValue[8]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("10")) {
                countByValue[9]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("J")) {
                countByValue[10]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("Q")) {
                countByValue[11]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("K")) {
                countByValue[12]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getValue().equals("A")) {
                countByValue[13]++;
                countByValue[0]++;
                cards.remove(cards.getFirst());
            }
        }
        cards = temp; //fill cards back up
        for (int i = 0; i < cardSize; i++) { //count amount of cards with each suit
            if (cards.getFirst().getSuit().equals("C")) {
                countBySuit[0]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getSuit().equals("D")) {
                countBySuit[1]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getSuit().equals("H")) {
                countBySuit[2]++;
                cards.remove(cards.getFirst());
            }
            else if (cards.getFirst().getSuit().equals("S")) {
                countBySuit[3]++;
                cards.remove(cards.getFirst());
            }

        }
        cards = temp;
        int flush, fourOfAKind, threeOfAKind, twoOfAKind, twoPair; //ints used for find function
        flush = Arrays.asList(countBySuit).indexOf(5); //check if there are five of a suit
        //flush = find(countBySuit, countBySuit + 4, 5);
        fourOfAKind = Arrays.asList(countByValue).indexOf(4); //check if there are four of a card value
        //fourOfAKind = find(countByValue, countByValue + 14, 4);
        threeOfAKind = Arrays.asList(countByValue).indexOf(3); //check if there are three of a card value
        //threeOfAKind = find(countByValue, countByValue + 14, 3);
        twoOfAKind = Arrays.asList(countByValue).indexOf(2); //check if there are two of a card value
        //twoOfAKind = find(countByValue, countByValue + 14, 2);
        Integer[] newArray = Arrays.copyOfRange(countByValue, twoOfAKind + 1, countByValue.length);
        twoPair = Arrays.asList(newArray).indexOf(2); //check for a second instance of two cards of a value
        //twoPair = find(countByValue + twoPosition + 1, countByValue + 14, 2);
        ArrayList<Integer> positions = whereExists(0, 13, countByValue); //vector of where the values are (to check for straights)
        ArrayList<Integer> isStraightResults;
        isStraightResults = isStraight(positions);
        if (flush != -1 && isStraightResults.get(0) == 1) { //check for straight flush
            output.add(9);
            output.add(isStraightResults.get(1)); //add value of straight high card
            return output;
        }
        else if (fourOfAKind != -1) { //check for four of a kind
            output.add(8);
            for (int i = 0; i < 14; i++) { //add value of four of a kind
                if (countByValue[i] == 4) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of 5th card
                if (countByValue[i] != 0 && countByValue[i] != 4) {
                    output.add(i);
                    break;
                }
            }
            return output;
        }
        else if (threeOfAKind != -1 && twoOfAKind != -1) { //check for a full house
            output.add(7);
            for (int i = 13; i >= 0; i--) { //add value of the three of a kind
                if (countByValue[i] == 3) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of the two of a kind
                if (countByValue[i] == 2) {
                    output.add(i);
                    break;
                }
            }
            return output;
        }
        else if (flush != -1) { //check for flush
            output.add(6);
            ArrayList<Integer> result = new ArrayList();
            String input = new String();
            for (int i = 13; i >= 0; i--) {
                if (countBySuit[i] == 5) {
                    if (i == 0)
                        input = "C";
                    else if (i == 1)
                        input = "D";
                    else if (i == 2)
                        input = "H";
                    else
                        input = "S";
                    break;
                }
            }
            result = flushHighCards(cards, input);
            for (int i = 0; i < result.size(); i++) {
                output.add(result.get(i));
            }
            return output;
        }
        else if (isStraightResults.get(0) == 1) { //check for straight
            output.add(5);
            output.add(isStraightResults.get(1));
            output.add(isStraightResults.get(2));
            output.add(isStraightResults.get(3));
            output.add(isStraightResults.get(4));
            output.add(isStraightResults.get(5));
            return output;
        }
        else if (threeOfAKind != -1) { //check for three of a kind
            output.add(4);
            for (int i = 13; i >= 0; i--) { //add value of the three of a kind
                if (countByValue[i] == 3) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of highest card not in 3 of a kind
                if (countByValue[i] != 0 && countByValue[i] != 3) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of the next highest card not in 3 of a kind
                if (countByValue[i] != 0 && countByValue[i] != 3 && i != output.get(2)) {
                    output.add(i);
                    break;
                }
            }
            return output;
        }
        else if (twoOfAKind != -1 && twoPair != -1) { //check for two pair
            output.add(3);
            for (int i = 13; i >= 0; i--) { //add value of the highest two of a kind
                if (countByValue[i] == 2) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of the next highest two of a kind
                if (countByValue[i] == 2 && i != output.get(1)) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of the next highest two of a kind
                if (countByValue[i] != 0 && countByValue[i] != 2) {
                    output.add(i);
                    break;
                }
            }
            return output;
        }
        else if (twoOfAKind != -1) { //check for pair
            output.add(2);
            for (int i = 13; i >= 0; i--) { //add value of the two of a kind
                if (countByValue[i] == 2) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of highest card not in 2 of a kind
                if (countByValue[i] != 0 && countByValue[i] != 2) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of the next highest card not in 2 of a kind
                if (countByValue[i] != 0 && countByValue[i] != 2 && i != output.get(2)) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of the next highest card not in 2 of a kind
                if (countByValue[i] != 0 && countByValue[i] != 2 && i != output.get(2) && i != output.get(3)) {
                    output.add(i);
                    break;
                }
            }
            return output;
        }
        else { //check for high card
            output.add(1);
            for (int i = 13; i >= 0; i--) { //add value of highest card
                if (countByValue[i] != 0) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of next highest card
                if (countByValue[i] != 0 && i != output.get(1)) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of next highest card
                if (countByValue[i] != 0 && i != output.get(1) && i != output.get(2)) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of next highest card
                if (countByValue[i] != 0 && i != output.get(1) && i != output.get(2) && i != output.get(3)) {
                    output.add(i);
                    break;
                }
            }
            for (int i = 13; i >= 0; i--) { //add value of next highest card
                if (countByValue[i] != 0 && i != output.get(1) && i != output.get(2) && i != output.get(3) && i != output.get(4)) {
                    output.add(i);
                    break;
                }
            }
            return output;
        }
    }
}
