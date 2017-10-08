package com.dobrica.android.recke.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.dobrica.android.recke.contractViewPresenter.MainContract;
import com.dobrica.android.recke.viewActivity.MainActivity;

/**
 * Created by DOBAR on 9/10/2017.
 */

public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.Main view;
    private SharedPreferences prefs;
    private String strSound = "sound";
    private Context context;


    public MainPresenter(MainActivity view, Context context) {
        this.view = view;
        this.context = context;

    }

    @Override
    public void checkSound() {
        prefs = context.getSharedPreferences("sound", Context.MODE_PRIVATE);
        String ore = prefs.getString("sound", "on");
        if (ore.equals("off")) {
            view.soundDecisions(true);
        } else {
            view.soundDecisions(false);
        }
    }

    @Override
    public void onBtnNewGameClicked() {
        view.startInputNamesActivity();
    }

    @Override
    public void onBtnInfoClicked() {
        view.startInfoActivity();
    }

    @Override
    public void soundOff(Boolean btnSondOff) {

        prefs = context.getSharedPreferences(strSound, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (btnSondOff) {
            editor.putString("sound", "off");
            editor.commit();
        } else {
            editor.putString("sound", "on");
            editor.commit();
        }
    }

    @Override
    public void btnLanguageClicked(Boolean en) {
        if (en) {
            view.setLanguageToLoad("en");
            view.callOnResume();
        } else {
            view.setLanguageToLoad("");
            view.callOnResume();
        }
    }
}