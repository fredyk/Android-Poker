package com.ryan.poker;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        Bundle extras = getIntent().getExtras();
        final String[] playerNameArray = extras.getStringArray("com.ryan.poker.playerNameArray");
        final int[] playerMoneyArray = extras.getIntArray("com.ryan.poker.playerMoneyArray");
        final int blind = extras.getInt("com.ryan.poker.blind");
        final int smallBlind = extras.getInt("com.ryan.poker.smallBlind");
        final int gameRound = extras.getInt("com.ryan.poker.gameRound");

        Board currentGame = new Board();
        ArrayList<Player> players = Game.playerSetup(playerNameArray);

        LinearLayout playerNameScroll = (LinearLayout) findViewById(R.id.PlayerInfo);
        TextView playerInformation = new TextView(this);
        String playerInformationString = new String();
        for(int i = 0; i < players.size() - 1; i++){
            playerInformationString += players.get(i).printName() + " - $" + players.get(i).printMoney() + "   ";
        }
        playerInformationString += players.get(players.size() - 1).printName() + " - $" + players.get(players.size() - 1).printMoney();
        playerInformation.setText(playerInformationString);
        playerInformation.setTextSize(18);
        playerNameScroll.addView(playerInformation);

    }
}
