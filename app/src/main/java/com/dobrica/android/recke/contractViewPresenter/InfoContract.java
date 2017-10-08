package com.dobrica.android.recke.contractViewPresenter;

import com.dobrica.android.recke.model.ReckeModel;

import java.util.ArrayList;

/**
 * Created by DOBAR on 9/10/2017.
 */

public interface InfoContract {

    interface InfoTabbed {

        void deleteBtnText();

        void startNewActivity();

        void callRecreate();
    }

    interface InfoPresenter {

        ArrayList<ReckeModel> getMyModles();

        void openNotes(int position);

        void delete(int position);

        void delete();
    }
}
