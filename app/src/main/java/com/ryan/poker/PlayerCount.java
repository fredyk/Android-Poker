package com.ryan.poker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlayerCount extends AppCompatActivity {

    int playerCount = 2;
    int playerMoney = 20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_count);
    }

    public void increasePlayerCount(View view) {
        if(playerCount < 10)
            playerCount = playerCount + 1;
        displayPlayerCount(playerCount);
    }

    public void decreasePlayerCount(View view) {
        if(playerCount > 2)
            playerCount = playerCount - 1;
        displayPlayerCount(playerCount);
    }

    private void displayPlayerCount(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.playerCount);
        displayInteger.setText(Integer.toString(number));
    }

    public void increasePlayerMoney(View view){
        playerMoney = playerMoney + 5000;
        displayPlayerMoney(playerMoney);
    }

    public void decreasePlayerMoney(View view){
        if(playerMoney > 5000)
            playerMoney = playerMoney - 5000;
        displayPlayerMoney(playerMoney);
    }

    public void displayPlayerMoney(int number){
        TextView displayInteger = (TextView) findViewById(R.id.playerMoney);
        displayInteger.setText("$" + Integer.toString(number));
    }

    public void sendPlayerCount(View view){
        Intent intent = new Intent(this, NamePlayers.class);
        Bundle extras = new Bundle();
        extras.putInt("com.ryan.poker.playerAmount", playerCount);
        extras.putInt("com.ryan.poker.playerStartingMoney", playerMoney);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
