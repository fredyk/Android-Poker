package com.ryan.poker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayGameUserInput extends Fragment implements View.OnClickListener {

    public interface Listener {
        public void onFoldButtonClicked();
        public void onCallButtonClicked();
        public void onBetButtonClicked(int betAmount);
    }

    PlayGameUserInput.Listener mListener = null;

    Board currentGame;
    ArrayList<Player> players;
    int loop;

    public void setArguments(Board iCurrentGame, ArrayList<Player> iPlayers, int iLoop){
        currentGame = iCurrentGame;
        players = iPlayers;
        loop = iLoop;
    }

    public void updateUI(Board currentGame, ArrayList<Player> players, int iLoop){
        loop = iLoop;
        if (getActivity() == null) return;
        TextView playerInformation = (TextView)getActivity().findViewById(R.id.PlayGameUserInput_playerInformation);
        String playerInformationString = new String();
        for (int i = 0; i < players.size() - 1; i++) {
            if (players.get(i).printState().equals("folded"))
                playerInformationString += players.get(i).printName() + " (folded)- $" + players.get(i).printMoney() + "   ";
            else
                playerInformationString += players.get(i).printName() + " - $" + players.get(i).printMoney() + "   ";
        }
        if (players.get(players.size() - 1).printState().equals("folded"))
            playerInformationString += players.get(players.size() - 1).printName() + " (folded)- $" + players.get(players.size() - 1).printMoney();
        else
            playerInformationString += players.get(players.size() - 1).printName() + " - $" + players.get(players.size() - 1).printMoney();
        playerInformation.setText(playerInformationString);
        TextView PlayerName = (TextView)getActivity().findViewById(R.id.PlayerName);
        PlayerName.setText("It is now your turn " + players.get(loop).printName());
        TextView River = (TextView)getActivity().findViewById(R.id.River);
        River.setText("River: " + currentGame.getRiver().print());
        Button Call = (Button)getActivity().findViewById(R.id.Call);
        Call.setText(Game.callButton(players.get(loop), currentGame));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_play_game_user_input, container, false);

        LinearLayout playerNameScroll = (LinearLayout)v.findViewById(R.id.PlayerInfo);
        TextView playerInformation = new TextView(getActivity());
        playerInformation.setId(R.id.PlayGameUserInput_playerInformation);
        String playerInformationString = new String();
        for (int i = 0; i < players.size() - 1; i++) {
            if (players.get(i).printState().equals("folded"))
                playerInformationString += players.get(i).printName() + " (folded)- $" + players.get(i).printMoney() + "   ";
            else
                playerInformationString += players.get(i).printName() + " - $" + players.get(i).printMoney() + "   ";
        }
        if (players.get(players.size() - 1).printState().equals("folded"))
            playerInformationString += players.get(players.size() - 1).printName() + " (folded)- $" + players.get(players.size() - 1).printMoney();
        else
            playerInformationString += players.get(players.size() - 1).printName() + " - $" + players.get(players.size() - 1).printMoney();
        playerInformation.setText(playerInformationString);
        playerInformation.setTextSize(18);
        playerNameScroll.addView(playerInformation);
        TextView PlayerName = (TextView)v.findViewById(R.id.PlayerName);
        PlayerName.setText("It is now your turn " + players.get(loop).printName());
        TextView River = (TextView)v.findViewById(R.id.River);
        River.setText("River: " + currentGame.getRiver().print());
        Button Hand = (Button)v.findViewById(R.id.Hand);
        Hand.setOnClickListener(this);
        Button Call = (Button)v.findViewById(R.id.Call);
        Call.setText(Game.callButton(players.get(loop), currentGame));
        Call.setOnClickListener(this);
        Button Fold = (Button)v.findViewById(R.id.Fold);
        Fold.setOnClickListener(this);
        Button Bet = (Button)v.findViewById(R.id.Bet);
        Bet.setOnClickListener(this);

        return v;
    }

    public void setListener(PlayGameUserInput.Listener l) {
        mListener = l;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Hand:
                onHandButtonClicked();
                break;
            case R.id.Fold:
                mListener.onFoldButtonClicked();
                break;
            case R.id.Call:
                mListener.onCallButtonClicked();
                break;
            case R.id.Bet:
                openBetDialog();
                break;
        }
    }

    public void onHandButtonClicked(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Hand")
                .setMessage(players.get(loop).getHand().print())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { }
                }).show();
    }

    public void openBetDialog(){
        final int minBet = currentGame.getMaxBet() - players.get(loop).getAmountBet();

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout displayBetAmount = new LinearLayout(getActivity());
        displayBetAmount.setOrientation(LinearLayout.HORIZONTAL);
        displayBetAmount.setGravity(Gravity.CENTER);

        final TextView betAmountTitle = new TextView(getActivity());
        betAmountTitle.setText("Bet: $");
        displayBetAmount.addView(betAmountTitle);

        final EditText showBetAmount = new EditText(getActivity());
        showBetAmount.setText(Integer.toString(minBet));
        showBetAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
        displayBetAmount.addView(showBetAmount);

        layout.addView(displayBetAmount);
        showBetAmount.setSelection(showBetAmount.getText().length());

        final SeekBar betAmount = new SeekBar(getActivity());
        betAmount.setMax(players.get(loop).printMoney() - minBet);
        betAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showBetAmount.setText(Integer.toString(betAmount.getProgress() + minBet));
                showBetAmount.setSelection(showBetAmount.getText().length());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        layout.addView(betAmount);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Bet")
                .setMessage("Choose amount to bet:")
                .setView(layout)
                .setPositiveButton("Bet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(Integer.parseInt(showBetAmount.getText().toString()) < minBet || Integer.parseInt(showBetAmount.getText().toString()) > players.get(loop).printMoney()){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("Error")
                                    .setMessage("Bet amount must be between $" + minBet + " and $" + players.get(loop).printMoney())
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) { }
                                    }).show();
                        }
                        else
                            mListener.onBetButtonClicked(Integer.parseInt(showBetAmount.getText().toString()));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) { }
                }).show();
    }
}
