package com.ryan.poker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlayerCount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_count);
    }

    public void sendPlayerCount(View view){
        Intent intent = new Intent(this, NamePlayers.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        int playerAmount = Integer.parseInt(editText.getText().toString());
        intent.putExtra("com.ryan.poker.playerAmount", playerAmount);
        startActivity(intent);
    }
}
