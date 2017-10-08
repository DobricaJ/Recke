package com.dobrica.android.recke.contractViewPresenter;


public interface InputNamesContract {

    //View
    interface InputNames {

        void startNewActivity();

        String getP1NameEntry();

        String getP2NameEntry();
    }

    //presenter
    interface InputNamesPresenter {

        void onBtnStart();
    }
}
