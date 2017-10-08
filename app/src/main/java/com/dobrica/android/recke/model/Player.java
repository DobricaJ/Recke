package com.dobrica.android.recke.model;

import java.io.Serializable;
import java.util.ArrayList;



public class Player implements Serializable{

    private String playerName;
    private int recke;
    private int finalScore;


    private ArrayList<Integer> round = new ArrayList<>();

    public void setRecke() {
        this.recke++;
    }

    public int getRecke() {
        return recke;
    }

    public void setFinalScore() {
        int roundSum =0;
        for(Integer a : round)
            roundSum += a;
        this.finalScore = recke + roundSum;

    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setScore(int score) {
        round.add(score);
    }

    public ArrayList<Integer> getScore() {
        return round;
    }

    public int getFinalScore() {
        return finalScore;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", recke=" + recke +
                ", finalScore=" + finalScore +
                ", round=" + round +
                '}';
    }
}
