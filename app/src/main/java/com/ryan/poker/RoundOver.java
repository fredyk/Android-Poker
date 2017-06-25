package com.ryan.poker;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RoundOver extends Fragment implements View.OnClickListener {

    public interface Listener {
        public void onShowStateButtonClicked();
    }

    RoundOver.Listener mListener = null;

    int playerAmount;
    ArrayList<String> winnerInfo;
    String river;

    public void setArguments(int iPlayerAmount, ArrayList<String> iWinnerInfo, String iRiver){
        playerAmount = iPlayerAmount;
        winnerInfo = iWinnerInfo;
        river = iRiver;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_round_over, container, false);

        LinearLayout displayWinners = (LinearLayout)v.findViewById(R.id.DisplayWinners);
        displayWinners.setGravity(1);

        TextView winners = new TextView(getActivity());
        winners.setGravity(Gravity.CENTER);
        String winnerNames = new String();
        for(int i = 1; i < (winnerInfo.size() - (playerAmount*3)); i++)
            winnerNames += winnerInfo.get(i);
        winners.setText(winnerNames);
        winners.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        winners.setTextSize(34);
        winners.setTypeface(null, Typeface.BOLD);
        displayWinners.addView(winners);

        TextView riverText = new TextView(getActivity());
        riverText.setText("River - " + river);
        riverText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        riverText.setTextSize(24);
        displayWinners.addView(riverText);

        TextView[] playerTextView = new TextView[playerAmount];
        int j = 0;
        for(int i = (winnerInfo.size() - (playerAmount*3)); i < (winnerInfo.size()); i=i+3){
            playerTextView[j] = new TextView(getActivity());
            playerTextView[j].setText(winnerInfo.get(i) + winnerInfo.get(i+1) + winnerInfo.get(i+2));
            playerTextView[j].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            playerTextView[j].setTextSize(24);
            displayWinners.addView(playerTextView[j]);
            j++;
        }

        Button Enter = new Button(getActivity());
        Enter.setText("Continue");
        Enter.setTextSize(14);
        Enter.setOnClickListener(this);
        Enter.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        displayWinners.addView(Enter);
        return v;
    }

    public void setListener(RoundOver.Listener l) {
        mListener = l;
    }

    @Override
    public void onClick(View v){
        mListener.onShowStateButtonClicked();
    }
}
