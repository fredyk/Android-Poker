package com.ryan.poker;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameState extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_state);

        Bundle extras = getIntent().getExtras();
        final String[] playerNameArray = extras.getStringArray("com.ryan.poker.playerNameArray");
        final int[] playerMoneyArray = extras.getIntArray("com.ryan.poker.playerMoneyArray");
        final int blind = extras.getInt("com.ryan.poker.blind");
        final int smallBlind = extras.getInt("com.ryan.poker.smallBlind");
        final int gameRound = extras.getInt("com.ryan.poker.gameRound");
        LinearLayout playerNameScroll = (LinearLayout) findViewById(R.id.LinearLayout);
        playerNameScroll.setGravity(1);

        TextView DisplayRound = new TextView(this);
        DisplayRound.setText("Round " + gameRound);
        DisplayRound.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        DisplayRound.setTextSize(56);
        DisplayRound.setTypeface(null, Typeface.BOLD);
        playerNameScroll.addView(DisplayRound);

        TextView BlindTitle = new TextView(this);
        BlindTitle.setText("Blinds");
        BlindTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        BlindTitle.setTextSize(34);
        BlindTitle.setTypeface(null, Typeface.BOLD);
        playerNameScroll.addView(BlindTitle);

        TextView Blind1 = new TextView(this);
        Blind1.setText(playerNameArray[blind] + " - $" + smallBlind);
        Blind1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Blind1.setTextSize(24);
        playerNameScroll.addView(Blind1);

        TextView Blind2 = new TextView(this);
        Blind2.setText(playerNameArray[blind+1] + " - $" + smallBlind*2);
        Blind2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Blind2.setTextSize(24);
        playerNameScroll.addView(Blind2);

        TextView PlayerTitle = new TextView(this);
        PlayerTitle.setText("Players");
        PlayerTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        PlayerTitle.setTextSize(34);
        PlayerTitle.setTypeface(null, Typeface.BOLD);
        playerNameScroll.addView(PlayerTitle);

        TextView[] playerTextView = new TextView[playerNameArray.length];
        for(int i = 0; i < playerNameArray.length; i++){
            playerTextView[i] = new TextView(this);
            playerTextView[i].setText(playerNameArray[i] + " - $" + playerMoneyArray[i]);
            playerTextView[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            playerTextView[i].setTextSize(24);
            playerNameScroll.addView(playerTextView[i]);
        }

        Button Enter = new Button(this);
        Enter.setText("Continue");
        Enter.setTextSize(14);
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPlayerInfo(playerNameArray, playerMoneyArray, blind, smallBlind, gameRound);
            }
        });
        Enter.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        playerNameScroll.addView(Enter);
    }

    public void sendPlayerInfo(String[] playerNameArray, int[] playerMoneyArray, int blind, int smallBlind, int gameRound){
        Intent intent = new Intent (this, PlayGame.class);
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
