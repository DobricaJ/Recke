package com.dobrica.android.recke.presenter;

import android.content.Context;
import com.dobrica.android.recke.contractViewPresenter.InputNamesContract;
import com.dobrica.android.recke.model.ReckeModel;
import com.dobrica.android.recke.model.data.SerializableManager;
import com.dobrica.android.recke.viewActivity.InputNamesActivity;

/**
 * Presenter for InputNamesActiivty
 */

public class InputNamesPresenter implements InputNamesContract.InputNamesPresenter {

    private InputNamesContract.InputNames view;
    private ReckeModel model;
    private Context context;

    public InputNamesPresenter(InputNamesActivity view) {
        this.view = view;
        this.model = new ReckeModel();
        this.context = view.getApplicationContext();
    }

    // OnClick, prezenter get names from view, set name in model, save model and start NotesActivity
    @Override
    public void onBtnStart() {

        String p1 = view.getP1NameEntry();
        String p2 = view.getP2NameEntry();
        if(p1.isEmpty()) p1 = ("Tim A");
        if(p2.isEmpty()) p2 = ("Tim B");
        model.player1.setPlayerName(p1);
        model.player2.setPlayerName(p2);
        SerializableManager.saveSerializable(context, model, "MyModels.ser");
        view.startNewActivity();
    }
}
