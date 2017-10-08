package com.dobrica.android.recke.viewActivity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dobrica.android.recke.R;

/**
 * Created by DOBAR on 9/21/2017.
 */

public class ReckeInfoTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recke_info_tab, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textTablic= (TextView) view.findViewById(R.id.reckeText);
        textTablic.setText("Kako se koristi applikacija \n bla bla \n ovo ono");
    }
}
