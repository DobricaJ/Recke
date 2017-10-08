package com.dobrica.android.recke.presenter;

import android.content.Context;

import com.dobrica.android.recke.contractViewPresenter.ScoreContract;
import com.dobrica.android.recke.model.ReckeModel;
import com.dobrica.android.recke.model.data.SerializableManager;
import com.dobrica.android.recke.viewActivity.ScoreActivity;

/**
 * Created by DOBAR on 9/10/2017.
 */

public class ScorePresenter implements ScoreContract.ScorePresenter {

    private ScoreContract.Score view;
    private ReckeModel model;
    private int max;
    private int scoreP1;
    private int scoreP2;
    
    private Context context;

    public ScorePresenter(ScoreActivity view, Context context) {
        this.view = view;
        this.context = context;
        this.model = ReckeModel.readSerializable(context, "MyModels.ser");
        view.displayNames(model.player1.getPlayerName(), model.player2.getPlayerName());
        max = 25;
        scoreP1 = 13;
        scoreP2 = 12;
        view.displayScore(scoreP1, scoreP2);
    }


    @Override
    public void onBtnAddClicked() {
        condition();
        logic(true);
    }

    @Override
    public void onBtnSubClicked() {
        condition();
        logic(false);
    }

    @Override
    public void onBtnPatClicked() {
        if (max != 25) {
            max = 25;
            scoreP1 = 13;
            scoreP2 = 12;
            view.changePatButton("@drawable/tref");
            view.displayScore(scoreP1, scoreP2);
        } else {
            max = 22;
            scoreP1 = 11;
            scoreP2 = 11;

            view.changePatButton("@drawable/herc");
            view.displayScore(scoreP1, scoreP2);
        }
    }

    @Override
    public void onBtnEnterClicked() {
        model.player1.setScore(scoreP1);
        model.player2.setScore(scoreP2);
        model.player1.setFinalScore();
        model.player2.setFinalScore();
        SerializableManager.saveSerializable(context, model, "MyModels.ser");
        view.startNewActivity();
    }

    @Override
    public void foraP1isChanged(boolean b) {
        model.setForaPlayer1(b);
    }

    @Override
    public void foraP2isChanged(boolean b) {
        model.setForaPlayer2(b);
    }

    public void condition() {
        int i[] = view.getTextEdit();
        if (i[0] + i[1] == max) {
            scoreP1 = i[0];
            scoreP2 = i[1];
        }
    }

    public void logic(boolean b) {
        if (b == true) {
            if (scoreP1 < max & scoreP2 > 0) {
                scoreP1++;
                scoreP2--;
            }

            view.displayScore(scoreP1, scoreP2);
        }
        else {
            if (scoreP2 < max & scoreP1 > 0) {
                scoreP2++;
                scoreP1--;
            }
            view.displayScore(scoreP1, scoreP2);
        }
    }

}
