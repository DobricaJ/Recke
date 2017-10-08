package com.dobrica.android.recke.presenter;

import android.content.Context;

import com.dobrica.android.recke.contractViewPresenter.InfoContract;
import com.dobrica.android.recke.model.ReckeModel;
import com.dobrica.android.recke.model.data.SerializableManager;
import com.dobrica.android.recke.viewActivity.InfoTabbedActivity;

import java.util.ArrayList;

/**
 * Created by DOBAR on 9/10/2017.
 */

public class InfoPresenter implements InfoContract.InfoPresenter {

    private ArrayList<ReckeModel> modelsForHystory;
    private InfoContract.InfoTabbed view;
    public ReckeModel model;
    private Context context;

    public InfoPresenter(InfoTabbedActivity view, Context context) {
        this.context = context;
        this.view = view;
        this.model = new ReckeModel();
        try {
            this.modelsForHystory = SerializableManager.readSerializable(context, "modelsForHistoryMap.ser");
        } catch (Exception e) {
            this.modelsForHystory = new ArrayList<ReckeModel>();
            modelsForHystory.add(new ReckeModel());
        }
    }

    @Override
    public ArrayList<ReckeModel> getMyModles() {
        if (modelsForHystory == null) {
            model.player1.setPlayerName("empty");
            view.deleteBtnText();
            modelsForHystory.add(model);
            modelsForHystory.add(model);

        }

        return modelsForHystory;
    }

    @Override
    public void openNotes(int position) {

        SerializableManager.saveSerializable(context, modelsForHystory.get(position), "MyModels.ser");
        view.startNewActivity();
    }

    @Override
    public void delete(int position) {
        modelsForHystory.remove(position);
        SerializableManager.saveArrayList(context, modelsForHystory, "modelsForHistoryMap.ser");
        view.callRecreate();
    }

    @Override
    public void delete() {
        SerializableManager.removeSerializable(context, "modelsForHistoryMap.ser");
        view.callRecreate();
    }

}
