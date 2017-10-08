package com.dobrica.android.recke.contractViewPresenter;

/**
 * Created by DOBAR on 9/10/2017.
 */

public interface MainContract {

    interface Main {

        void startInputNamesActivity();

        void startInfoActivity();

        void soundDecisions(Boolean isChecked);

        void setLanguageToLoad(String languageToLoad);

        void callOnResume();
    }

    interface MainPresenter {

        void onBtnNewGameClicked();

        void onBtnInfoClicked();

        void checkSound();

        void soundOff(Boolean btnSondOff);

        void btnLanguageClicked(Boolean en);
    }
}
