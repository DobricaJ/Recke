package com.dobrica.android.recke.contractViewPresenter;

/**
 * Created by DOBAR on 9/10/2017.
 */

public interface NotesContract {

    interface Notes {

        void startNewActivity();

        void showNames(String p1, String p2);

        void showRecke(String[] sss);

        void showScore(String scoreP1andP2);

        void showFinalScore(String p1, String p2);

        void disableButtons();

        void playAnim();

    }

    interface NotesPresenter {

        void onBtnPlayer1Clicked();

        void onBtnPlayer2Clicked();

        void onBtnScoreClicked();

        void onExitYes();

        void mpStop();

        void onBtnPlayAgainClicked();

    }

}
