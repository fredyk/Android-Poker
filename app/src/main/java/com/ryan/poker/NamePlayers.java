package com.ryan.poker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NamePlayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_players);
        final Context context = this;

        Intent intent = getIntent();
        final int playerAmount = intent.getIntExtra("com.ryan.poker.playerAmount", 0);

        final EditText[] playerNames = new EditText[playerAmount];
        TextView[] playerLabels = new TextView[playerAmount];
        LinearLayout playerNameScroll = (LinearLayout) findViewById(R.id.LinearLayout);
        playerNameScroll.setGravity(1);
        for(int i = 0; i < playerAmount; i++) {
            playerLabels[i] = new TextView(this);
            playerLabels[i].setText("Player " + (i+1) + " name:");
            playerLabels[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            playerLabels[i].setMaxLines(1);
            playerLabels[i].setTextSize(25);
            playerNameScroll.addView(playerLabels[i]);
            playerNames[i] = new EditText(this);
            playerNames[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            playerNames[i].setTextSize(25);
            playerNameScroll.addView(playerNames[i]);
        }

        Button Enter = new Button(this);
        Enter.setText("Continue");
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean duplicates=false;
                for (int j=0;j<playerAmount;j++) {
                    for (int k = j + 1; k < playerAmount; k++) {
                        if (k != j && playerNames[k].getText().toString().equals(playerNames[j].getText().toString()))
                            duplicates = true;
                    }
                }
                if(!duplicates)
                    sendPlayerCount(playerNames[0].getText().toString(), playerNames[1].getText().toString());
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                    builder.setTitle("Error")
                            .setMessage("All names must be unique.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) { }
                            }).setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });
        Enter.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        playerNameScroll.addView(Enter);
    }

    public void sendPlayerCount(String player1, String player2){
        Intent intent = new Intent (this, GameState.class);
        Bundle extras = new Bundle();
        extras.putString("player1Name",player1);
        extras.putString("player2Name",player2);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
