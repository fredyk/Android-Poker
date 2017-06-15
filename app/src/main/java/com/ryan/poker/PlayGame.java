package com.ryan.poker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        Bundle extras = getIntent().getExtras();
        String[] playerNameArray = extras.getStringArray("com.ryan.poker.playerNameArray");
        int[] playerMoneyArray = extras.getIntArray("com.ryan.poker.playerMoneyArray");
        int blind = extras.getInt("com.ryan.poker.blind");
        int smallBlind = extras.getInt("com.ryan.poker.smallBlind");
        int gameRound = extras.getInt("com.ryan.poker.gameRound");

        Board currentGame = new Board();
        ArrayList<Player> players = Game.playerSetup(playerNameArray, playerMoneyArray);

        CardSet deck = new CardSet();
        deck = Functions.populate(deck); //fill deck w/ all 52 cards
        deck.shuffle(); //randomize deck

        Game.roundSetup(currentGame, players, blind, smallBlind, deck);

        int loop = (blind + 2) % players.size();
        int round = 1;

        screenSetup(currentGame, players, round, deck, loop, blind, smallBlind, gameRound);
    }

    public void screenSetup(final Board currentGame, final ArrayList<Player> players, int round, CardSet deck, int loop, int blind, int smallBlind, int gameRound){
        ArrayList<String> roundOver;
        roundOver = Game.iterateRound(currentGame, players, round, deck);
        if(!roundOver.get(0).equals("continue") && !roundOver.get(0).equals("nextRound")){
            roundOver(players, blind, smallBlind, gameRound, roundOver);
        }
        else if(roundOver.get(0).equals("nextRound"))
            round++;
        if (!players.get(loop).printState().equals("folded")) {
            LinearLayout playerNameScroll = (LinearLayout) findViewById(R.id.PlayerInfo);
            playerNameScroll.removeAllViews();
            TextView playerInformation = new TextView(this);
            String playerInformationString = new String();
            for (int i = 0; i < players.size() - 1; i++) {
                if (players.get(i).printState().equals("folded"))
                    playerInformationString += players.get(i).printName() + " (folded)- $" + players.get(i).printMoney() + "   ";
                else
                    playerInformationString += players.get(i).printName() + " - $" + players.get(i).printMoney() + "   ";
            }
            if(players.get(players.size() - 1).printState().equals("folded"))
                playerInformationString += players.get(players.size() - 1).printName() + " (folded)- $" + players.get(players.size() - 1).printMoney();
            else
                playerInformationString += players.get(players.size() - 1).printName() + " - $" + players.get(players.size() - 1).printMoney();
            playerInformation.setText(playerInformationString);
            playerInformation.setTextSize(18);
            playerNameScroll.addView(playerInformation);
            TextView PlayerName = (TextView) findViewById(R.id.PlayerName);
            PlayerName.setText("It is now your turn " + players.get(loop).printName());
            TextView River = (TextView) findViewById(R.id.River);
            River.setText("River: " + currentGame.getRiver().print());
            Button Call = (Button) findViewById(R.id.Call);
            Call.setText(Game.callButton(players.get(loop), currentGame));
            final int finalRound = round;
            final CardSet finalDeck = deck;
            final int finalLoop = loop;
            final int finalBlind = blind;
            final int finalSmallBlind = smallBlind;
            final int finalGameRound = gameRound;
            Button Hand = (Button) findViewById(R.id.Hand);
            Hand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handButton(players, finalLoop);
                }
            });
            Button Fold = (Button) findViewById(R.id.Fold);
            Fold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    foldButton(currentGame, players, finalRound, finalDeck, finalLoop, finalBlind, finalSmallBlind, finalGameRound);
                }
            });
            Call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callButton(currentGame, players, finalRound, finalDeck, finalLoop, finalBlind, finalSmallBlind, finalGameRound);
                }
            });
        }
        else {
            loop = (loop + 1) % players.size();
            screenSetup(currentGame, players, round, deck, loop, blind, smallBlind, gameRound);
        }
    }

    public void handButton(ArrayList<Player> players, int loop) {
        final Context context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle("Hand")
                .setMessage(players.get(loop).getHand().print())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { }
                }).setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void foldButton(Board currentGame, ArrayList<Player> players, int round, CardSet deck, int loop, int blind, int smallBlind, int gameRound) {
        Game.takeTurn(players.get(loop), players, currentGame, "Fold");
        loop = (loop + 1) % players.size();
        screenSetup(currentGame, players, round, deck, loop, blind, smallBlind, gameRound);
    }

    public void callButton(Board currentGame, ArrayList<Player> players, int round, CardSet deck, int loop, int blind, int smallBlind, int gameRound) {
        Button Call = (Button)findViewById(R.id.Call);
        if(Call.getText() == "Check")
            Game.takeTurn(players.get(loop), players, currentGame, "Check");
        else
            Game.takeTurn(players.get(loop), players, currentGame, "Call");
        loop = (loop + 1) % players.size();
        screenSetup(currentGame, players, round, deck, loop, blind, smallBlind, gameRound);
    }

    public void betButton() {

    }

    public void roundOver(ArrayList<Player> players, int blind, int smallBlind, int gameRound, ArrayList<String> winnerInfo){
        for (int i = 0; i < players.size();) { //remove players if they busted
            if (players.get(i).printMoney() == 0) {
                players.remove(i);
                i = 0;
            }
            else
                i++;
        }
        if(players.size() == 1){
            gameOver(players.get(0).printName());
        }
        else {
            if (gameRound % 5 == 0) {
                smallBlind += 200;
            }
            gameRound = gameRound + 1;
            blind = (blind + 1) % players.size(); //move blind to next player
            String[] playerNameArray = new String[players.size()];
            int[] playerMoneyArray = new int[players.size()];
            for (int i = 0; i < players.size(); i++) {
                playerNameArray[i] = players.get(i).printName();
                playerMoneyArray[i] = players.get(i).printMoney();
            }
            sendWinnerInfo(winnerInfo, playerNameArray, playerMoneyArray, blind, smallBlind, gameRound);
        }
    }

    public void sendWinnerInfo(ArrayList<String> winnerInfo, String[] playerNameArray, int[] playerMoneyArray, int blind, int smallBlind, int gameRound){
        Intent intent = new Intent (this, RoundWinner.class);
        Bundle extras = new Bundle();
        extras.putStringArrayList("com.ryan.poker.winnerInfo", winnerInfo);
        extras.putStringArray("com.ryan.poker.playerNameArray",playerNameArray);
        extras.putIntArray("com.ryan.poker.playerMoneyArray",playerMoneyArray);
        extras.putInt("com.ryan.poker.blind",blind);
        extras.putInt("com.ryan.poker.smallBlind",smallBlind);
        extras.putInt("com.ryan.poker.gameRound",gameRound);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void gameOver(String winnerName){
        Intent intent = new Intent(this, GameOver.class);
        intent.putExtra("com.ryan.poker.winnerName", winnerName);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }
}
