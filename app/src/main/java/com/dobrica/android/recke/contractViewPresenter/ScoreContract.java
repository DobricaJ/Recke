package com.dobrica.android.recke.contractViewPresenter;

/**
 * Created by DOBAR on 9/10/2017.
 */

public interface ScoreContract {

    interface Score {

        void startNewActivity();

        void displayNames(String playerName1, String playerName2);

        void displayScore(int p1, int p2);

        void changePatButton(String id);

        int[] getTextEdit();
    }


    interface ScorePresenter {

        void onBtnAddClicked();

        void onBtnSubClicked();

        void onBtnPatClicked();

        void onBtnEnterClicked();

        void foraP1isChanged(boolean b);

        void foraP2isChanged(boolean b);

    }
}
