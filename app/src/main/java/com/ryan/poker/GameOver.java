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
        final String winnerName = intent.getStringExtra("com.ryan.poker.winnerName");

        TextView WinnerName = (TextView)findViewById(R.id.winnerName);
        WinnerName.setText(winnerName + " has won the game! Congratulations!");
    }
}
