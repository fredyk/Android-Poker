package com.ryan.poker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
        final Context context = this;
        EditText editText = (EditText) findViewById(R.id.editText);
        int playerAmount = Integer.parseInt(editText.getText().toString());
        if(playerAmount < 2){
            AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
            builder.setTitle("Error")
                    .setMessage("Invalid amount of players. Value must be at least 2.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) { }
                    }).setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else {
            Intent intent = new Intent(this, NamePlayers.class);
            intent.putExtra("com.ryan.poker.playerAmount", playerAmount);
            startActivity(intent);
        }
    }
}
