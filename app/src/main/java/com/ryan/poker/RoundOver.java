package com.ryan.poker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class RoundOver extends Fragment implements View.OnClickListener {

    public interface Listener {
        public void onShowStateButtonClicked();
    }

    RoundOver.Listener mListener = null;

    ArrayList<String> winnerInfo;

    public void setArguments(ArrayList<String> iWinnerInfo){
        winnerInfo = iWinnerInfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_round_over, container, false);
        Button test = (Button)v.findViewById(R.id.testButton);
        test.setOnClickListener(this);
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
