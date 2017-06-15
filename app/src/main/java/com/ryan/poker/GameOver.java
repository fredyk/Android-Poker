package com.ryan.poker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        String winnerName = intent.getStringExtra("com.ryan.poker.winnerName");

        TextView PlayerName = (TextView) findViewById(R.id.winner);
        PlayerName.setText(winnerName + " has won!");
    }

    @Override
    public void onBackPressed() {
    }
}
