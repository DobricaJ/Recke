package com.dobrica.android.recke.model;


import com.dobrica.android.recke.model.data.SerializableManager;

import java.io.Serializable;



/**
 * All this ReckeModel need is two players.
 */

public class ReckeModel extends SerializableManager implements Serializable {
    public Player player1;
    public Player player2;
    private String currentTime;
    private boolean foraPlayer1;
    private boolean foraPlayer2;


    public ReckeModel() {
        player1 = new Player();
        player2 = new Player();

    }
    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public void setForaPlayer1(boolean foraPlayer1) {
        this.foraPlayer1 = foraPlayer1;
    }

    public void setForaPlayer2(boolean foraPlayer2) {
        this.foraPlayer2 = foraPlayer2;
    }

    public int winner() {
        if(foraPlayer1 | foraPlayer2){

            if(foraPlayer1)
                if(player1.getFinalScore() >= 101) return 3;
            else return 4;

            if(foraPlayer2)
                if(player2.getFinalScore() >= 101) return 5;
            else return 6;


        }else {
            if (player1.getFinalScore() >= 101 | player2.getFinalScore() >= 101) {
                if (player1.getFinalScore() == player2.getFinalScore()) {
                    return 0;
                } else if (player1.getFinalScore() > player2.getFinalScore()) {
                    return 1;
                } else return 2;
            }
        }
        return 0;
    }
}
