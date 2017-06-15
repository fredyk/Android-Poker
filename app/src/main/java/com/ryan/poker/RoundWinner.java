package com.ryan.poker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RoundWinner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_winner);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> winnerInfo = extras.getStringArrayList("com.ryan.poker.winnerInfo");
        final String[] playerNameArray = extras.getStringArray("com.ryan.poker.playerNameArray");
        final int[] playerMoneyArray = extras.getIntArray("com.ryan.poker.playerMoneyArray");
        final int blind = extras.getInt("com.ryan.poker.blind");
        final int smallBlind = extras.getInt("com.ryan.poker.smallBlind");
        final int gameRound = extras.getInt("com.ryan.poker.gameRound");

        LinearLayout displayWinners = (LinearLayout) findViewById(R.id.DisplayWinners);
        displayWinners.setGravity(1);

        TextView winner = new TextView(this);
        winner.setText("Winner");
        displayWinners.addView(winner);
        Button Enter = new Button(this);
        Enter.setText("Continue");
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPlayerInfo(playerNameArray, playerMoneyArray, blind, smallBlind, gameRound);
            }
        });
        displayWinners.addView(Enter);
    }

    public void sendPlayerInfo(String[] playerNameArray, int[] playerMoneyArray, int blind, int smallBlind, int gameRound){
        Intent intent = new Intent (this, GameState.class);
        Bundle extras = new Bundle();
        extras.putStringArray("com.ryan.poker.playerNameArray",playerNameArray);
        extras.putIntArray("com.ryan.poker.playerMoneyArray",playerMoneyArray);
        extras.putInt("com.ryan.poker.blind",blind);
        extras.putInt("com.ryan.poker.smallBlind",smallBlind);
        extras.putInt("com.ryan.poker.gameRound",gameRound);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }
}
