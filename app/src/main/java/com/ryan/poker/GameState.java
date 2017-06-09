package com.ryan.poker;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class GameState extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_state);

        Bundle extras = getIntent().getExtras();
        String[] playerNameArray = extras.getStringArray("com.ryan.poker.playerNameArray");
        int[] playerMoneyArray = extras.getIntArray("com.ryan.poker.playerMoneyArray");
        int blind = extras.getInt("com.ryan.poker.blind");
        int smallBlind = extras.getInt("com.ryan.poker.smallBlind");
        int gameRound = extras.getInt("com.ryan.poker.gameRound");
        LinearLayout playerNameScroll = (LinearLayout) findViewById(R.id.LinearLayout);
        playerNameScroll.setGravity(1);

        TextView DisplayRound = new TextView(this);
        DisplayRound.setText("Round " + gameRound);
        DisplayRound.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        DisplayRound.setTextSize(60);
        DisplayRound.setTypeface(null, Typeface.BOLD);
        playerNameScroll.addView(DisplayRound);

        TextView BlindTitle = new TextView(this);
        BlindTitle.setText("Blinds");
        BlindTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        BlindTitle.setTextSize(45);
        BlindTitle.setTypeface(null, Typeface.BOLD);
        playerNameScroll.addView(BlindTitle);

        TextView Blind1 = new TextView(this);
        Blind1.setText(playerNameArray[blind] + " - $" + smallBlind);
        Blind1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Blind1.setTextSize(35);
        playerNameScroll.addView(Blind1);

        TextView Blind2 = new TextView(this);
        Blind2.setText(playerNameArray[blind+1] + " - $" + smallBlind*2);
        Blind2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Blind2.setTextSize(35);
        playerNameScroll.addView(Blind2);

        TextView PlayerTitle = new TextView(this);
        PlayerTitle.setText("Players");
        PlayerTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        PlayerTitle.setTextSize(45);
        PlayerTitle.setTypeface(null, Typeface.BOLD);
        playerNameScroll.addView(PlayerTitle);

        TextView[] playerTextView = new TextView[playerNameArray.length];
        for(int i = 0; i < playerNameArray.length; i++){
            playerTextView[i] = new TextView(this);
            playerTextView[i].setText(playerNameArray[i] + " - $" + playerMoneyArray[i]);
            playerTextView[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            playerTextView[i].setTextSize(35);
            playerNameScroll.addView(playerTextView[i]);
        }

        Button Enter = new Button(this);
        Enter.setText("Continue");
        Enter.setTextSize(20);
        Enter.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        playerNameScroll.addView(Enter);
    }
}
