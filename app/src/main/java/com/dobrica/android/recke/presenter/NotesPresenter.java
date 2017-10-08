package com.dobrica.android.recke.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import com.dobrica.android.recke.R;
import com.dobrica.android.recke.contractViewPresenter.NotesContract;
import com.dobrica.android.recke.model.ReckeModel;
import com.dobrica.android.recke.model.data.SerializableManager;
import com.dobrica.android.recke.viewActivity.NotesActivity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by DOBAR on 9/10/2017.
 */

public class NotesPresenter implements NotesContract.NotesPresenter {


    private NotesContract.Notes view;
    private ReckeModel model;
    private Context context;
    public MediaPlayer mp1;
    public MediaPlayer mp2;
    public MediaPlayer mp3;

    private String strSound;
    private SharedPreferences prefs;
    private ArrayList<ReckeModel> modelsForHystory;


    public NotesPresenter(NotesActivity view, Context context) {
        this.view = view;
        this.context = context;
        this.model = ReckeModel.readSerializable(context, "MyModels.ser");
        prefs = context.getSharedPreferences("sound", Context.MODE_PRIVATE);
        strSound = prefs.getString("sound", "on");

        view.showNames(model.player1.getPlayerName(), model.player2.getPlayerName());
        view.showScore(getScoreToString());
        view.showFinalScore(Integer.toString(model.player1.getFinalScore())
                , Integer.toString(model.player2.getFinalScore()));

        createMediaPlayers();
        checkWinner();
        drawRecke();
    }

    @Override
    public void onBtnPlayer1Clicked() {
        if (strSound.equals("on")) mp1.start();

        model.player1.setRecke();

        model.player1.setFinalScore();

        if (model.player1.getRecke() <= 30) {
            view.showRecke(selectPic(model.player1.getRecke(), "A"));
        }

        view.showFinalScore(Integer.toString(model.player1.getFinalScore())
                , Integer.toString(model.player2.getFinalScore()));

        checkWinner();

    }

    @Override
    public void onBtnPlayer2Clicked() {

        model.player2.setRecke();

        model.player2.setFinalScore();

        if (model.player2.getRecke() <= 30) {
            view.showRecke(selectPic(model.player2.getRecke(), "B"));
        }

        view.showFinalScore(Integer.toString(model.player1.getFinalScore())
                , Integer.toString(model.player2.getFinalScore()));

        if (strSound.equals("on")) mp2.start();

        checkWinner();

    }

    @Override
    public void onBtnScoreClicked() {
        if (strSound.equals("on")) mp3.stop();
        SerializableManager.saveSerializable(context, model, "MyModels.ser");
        view.startNewActivity();
    }

    @Override
    public void onBtnPlayAgainClicked() {

    }

    @Override
    public void onExitYes() {
        mp3.stop();

        model.setCurrentTime(DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));

        if (SerializableManager.readArrayList(context, "modelsForHistoryMap.ser") == null) {
            modelsForHystory = new ArrayList<ReckeModel>();
        } else {
            modelsForHystory = SerializableManager.readArrayList(context, "modelsForHistoryMap.ser");
        }

        modelsForHystory.add(model);

        SerializableManager.saveArrayList(context, modelsForHystory, "modelsForHistoryMap.ser");
    }

    @Override
    public void mpStop() {
        mp3.stop();
    }


    private void drawRecke() {
        for (int i = 0; i <= model.player1.getRecke(); i++) {
            view.showRecke(selectPic(i, "A"));
        }
        for (int i = 0; i <= model.player2.getRecke(); i++) {
            view.showRecke(selectPic(i, "B"));
        }
    }

    private void createMediaPlayers() {

        mp1 = MediaPlayer.create(context, R.raw.meow);
        mp2 = MediaPlayer.create(context, R.raw.woof);
        mp3 = MediaPlayer.create(context, R.raw.celebration_1);
    }

    private String getScoreToString() {
        ArrayList p1 = model.player1.getScore();
        ArrayList p2 = model.player2.getScore();

        StringBuilder builder = new StringBuilder();
        try {
            for (int i = 0; i <= p1.size(); i++) {
                builder.append(p1.get(i) + "\t\t\t" + p2.get(i) + "\n");
            }
        } catch (Exception e) {
            builder.append("00\t\t\t\t00" + "\n");
        }
        String scoreP1andP2 = builder.toString();
        return scoreP1andP2;
    }

    private String[] selectPic(int recke, String forID) {

        int i = (recke - 1) / 5 + 1;
        int f = (i - 1) * 5;
        String ID = forID + i;

        int reckeImageSuffix = recke - f;

        String reckeImageName = "@drawable/recke" + reckeImageSuffix;

        String[] sss = {ID, reckeImageName};
        return sss;
    }

    private void checkWinner() {
        switch (model.winner()) {
            case 0:
                break;
            case 1:
                view.showFinalScore("W" + Integer.toString(model.player1.getFinalScore())
                        , Integer.toString(model.player2.getFinalScore()));
                mp3.start();
                view.playAnim();
                view.disableButtons();

                break;
            case 2:
                view.showFinalScore(Integer.toString(model.player1.getFinalScore())
                        , "W" + Integer.toString(model.player2.getFinalScore()));
                mp3.start();
                view.playAnim();
                view.disableButtons();
                break;
            case 3:
                view.showFinalScore("W" + Integer.toString(model.player1.getFinalScore())
                        , Integer.toString(model.player2.getFinalScore()));
                mp3.start();
                view.playAnim();
                view.disableButtons();
                break;
            case 4:
                view.showFinalScore(Integer.toString(model.player1.getFinalScore())
                        , "W" + Integer.toString(model.player2.getFinalScore()));
                mp3.start();
                view.playAnim();
                view.disableButtons();
                break;
            case 5:
                view.showFinalScore(Integer.toString(model.player1.getFinalScore())
                        , "W" + Integer.toString(model.player2.getFinalScore()));
                mp3.start();
                view.playAnim();
                view.disableButtons();
                break;
            case 6:
                view.showFinalScore("W" + Integer.toString(model.player1.getFinalScore())
                        , Integer.toString(model.player2.getFinalScore()));
                mp3.start();
                view.playAnim();
                view.disableButtons();
                break;
            default:
                break;
        }
    }
}
