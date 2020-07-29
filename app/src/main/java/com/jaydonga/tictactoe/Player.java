package com.jaydonga.tictactoe;

public class Player {
    private PlayerNumber mNumber;
    private int mSymbol;

    enum PlayerNumber{
        Player1,
        Player2;
    }

    public Player(PlayerNumber number,int symbol ) {

        mNumber = number;
        mSymbol = symbol;
    }

    public PlayerNumber getNumber() {
        return mNumber;
    }

    public int getSymbol() {
        return mSymbol;
    }
}
