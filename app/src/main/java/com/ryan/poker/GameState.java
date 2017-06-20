package com.ryan.poker;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GameState extends Fragment implements View.OnClickListener {

    public interface Listener {
        public void onStartRoundButtonClicked();
    }

    Listener mListener = null;

    ArrayList<Player> players;
    int blind;
    int smallBlind;
    int gameRound;

    public void setArguments(ArrayList<Player> iPlayers, int iBlind, int iSmallBlind, int iGameRound){
        players = iPlayers;
        blind = iBlind;
        smallBlind = iSmallBlind;
        gameRound = iGameRound;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game_state, container, false);

        LinearLayout playerNameScroll = (LinearLayout)v.findViewById(R.id.LinearLayout);
        playerNameScroll.setGravity(1);

        TextView DisplayRound = new TextView(getActivity());
        DisplayRound.setText("Round " + gameRound);
        DisplayRound.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        DisplayRound.setTextSize(56);
        DisplayRound.setTypeface(null, Typeface.BOLD);
        playerNameScroll.addView(DisplayRound);

        TextView BlindTitle = new TextView(getActivity());
        BlindTitle.setText("Blinds");
        BlindTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        BlindTitle.setTextSize(34);
        BlindTitle.setTypeface(null, Typeface.BOLD);
        playerNameScroll.addView(BlindTitle);

        TextView Blind1 = new TextView(getActivity());
        Blind1.setText(players.get(blind).printName() + " - $" + smallBlind);
        Blind1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Blind1.setTextSize(24);
        playerNameScroll.addView(Blind1);

        TextView Blind2 = new TextView(getActivity());
        Blind2.setText(players.get((blind + 1) % players.size()).printName() + " - $" + smallBlind*2);
        Blind2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Blind2.setTextSize(24);
        playerNameScroll.addView(Blind2);

        TextView PlayerTitle = new TextView(getActivity());
        PlayerTitle.setText("Players");
        PlayerTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        PlayerTitle.setTextSize(34);
        PlayerTitle.setTypeface(null, Typeface.BOLD);
        playerNameScroll.addView(PlayerTitle);

        TextView[] playerTextView = new TextView[players.size()];
        for(int i = 0; i < players.size(); i++){
            playerTextView[i] = new TextView(getActivity());
            playerTextView[i].setText(players.get(i).printName() + " - $" + players.get(i).printMoney());
            playerTextView[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            playerTextView[i].setTextSize(24);
            playerNameScroll.addView(playerTextView[i]);
        }

        Button Enter = new Button(getActivity());
        Enter.setText("Continue");
        Enter.setTextSize(14);
        Enter.setOnClickListener(this);
        Enter.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        playerNameScroll.addView(Enter);

        return v;
    }

    public void setListener(Listener l) {
        mListener = l;
    }

    @Override
    public void onClick(View v){
        mListener.onStartRoundButtonClicked();
    }
}
