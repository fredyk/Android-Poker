package com.ryan.poker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameState extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_state);

        Bundle extras = getIntent().getExtras();
        String player1Name = extras.getString("player1Name");
        String player2Name = extras.getString("player2Name");

        TextView text1 = (TextView) findViewById(R.id.textView1);
        text1.setText(player1Name);
        TextView text2 = (TextView) findViewById(R.id.textView2);
        text2.setText(player2Name);
    }
}
