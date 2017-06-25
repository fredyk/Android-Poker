package com.ryan.poker;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class PlayGame extends AppCompatActivity implements GameState.Listener, PlayGameUserInput.Listener, RoundOver.Listener {
    //Fragments
    GameState gameState;
    PlayGameUserInput playGameUserInput;
    RoundOver roundOver;

    //initialize game variables
    Board currentGame = new Board();
    int playerStartingMoney;
    int smallBlind;
    int blind = 0; //start blind with first player
    int gameRound = 1; //start with first round
    ArrayList<Player> players = new ArrayList<>();
    int loop;
    int round = 1;
    CardSet deck = new CardSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //Get player names from previous page
        Bundle extras = getIntent().getExtras();
        String[] playerNameArray = extras.getStringArray("com.ryan.poker.playerNameArray");
        playerStartingMoney = extras.getInt("com.ryan.poker.playerStartingMoney", 0);
        smallBlind = playerStartingMoney / 50; //small blind starts as 1/50 of amount of money players have

        players = Game.playerSetup(playerNameArray, playerStartingMoney);
        loop = (blind + 2) % players.size();

        gameState = new GameState();
        playGameUserInput = new PlayGameUserInput();
        roundOver = new RoundOver();

        gameState.setListener(this);
        playGameUserInput.setListener(this);
        roundOver.setListener(this);

        //start by showing game state
        gameState.setArguments(players, blind, smallBlind, gameRound);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, gameState).commit();
    }

    // Switch UI to the given fragment
    void switchToFragment(Fragment newFrag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newFrag).commit();
    }

    @Override
    public void onStartRoundButtonClicked(){
        startRound();
    }

    @Override
    public void onFoldButtonClicked(){
        Game.turnAction(players.get(loop), players, currentGame, "Fold", 0);
        loop = (loop + 1) % players.size();
        takeTurn();
    }

    @Override
    public void onCallButtonClicked(){
        String callButtonText = Game.callButton(players.get(loop), currentGame);
        if(callButtonText.equals("Check"))
            Game.turnAction(players.get(loop), players, currentGame, "Check", 0);
        else
            Game.turnAction(players.get(loop), players, currentGame, "Call", 0);
        loop = (loop + 1) % players.size();
        takeTurn();
    }

    @Override
    public void onBetButtonClicked(int betAmount){
        Game.turnAction(players.get(loop), players, currentGame, "Bet", betAmount);
        loop = (loop + 1) % players.size();
        takeTurn();
    }

    @Override
    public void onShowStateButtonClicked(){
        gameState.setArguments(players, blind, smallBlind, gameRound);
        switchToFragment(gameState);
    }

    public void startRound(){
        deck = Functions.populate(deck); //fill deck w/ all 52 cards
        deck.shuffle();
        Game.roundSetup(currentGame, players, blind, smallBlind, deck); //deal cards and pay blinds
        playGameUserInput.setArguments(currentGame, players, loop);
        switchToFragment(playGameUserInput);
        takeTurn();
    }

    public void takeTurn(){
        ArrayList<String> roundState;
        roundState = Game.iterateBetRound(currentGame, players, round, deck);
        if(!roundState.get(0).equals("continue") && !roundState.get(0).equals("nextRound")){
            roundOver(roundState);
        }
        else {
            if(roundState.get(0).equals("nextRound"))
                round++;
            if (players.get(loop).printMoney() == 0) {
                players.get(loop).setState("active|b");
                loop = (loop + 1) % players.size();
                takeTurn();
            } else if (!players.get(loop).printState().equals("folded")) {
                playGameUserInput.updateUI(currentGame, players, loop);
            }  else {
                loop = (loop + 1) % players.size();
                takeTurn();
            }
        }
    }

    public void roundOver(ArrayList<String> winnerInfo){
        for (int i = 0; i < players.size();) { //remove players if they busted
            if (players.get(i).printMoney() == 0) {
                players.remove(i);
                i = 0;
            }
            else
                i++;
        }
        if(players.size() == 1){
            gameOver(players.get(0).printName());
        }
        else {
            int currentPlayers = 0;
            for (int i = 0; i < players.size(); i++) { //find amount of players still in round
                if (!players.get(i).printState().equals("folded"))
                    currentPlayers++;
            }
            roundOver.setArguments(currentPlayers, winnerInfo, currentGame.getRiver().print());
            switchToFragment(roundOver);
            Game.resetRound(currentGame, players);
            if (gameRound % 5 == 0)
                smallBlind += 200;
            blind = (blind + 1) % players.size();
            gameRound = gameRound + 1;
            loop = (blind + 2) % players.size();
            round = 1;
        }
    }

    public void gameOver(String winnerName){
        Intent intent = new Intent(this, GameOver.class);
        intent.putExtra("com.ryan.poker.winnerName", winnerName);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() { }
}
