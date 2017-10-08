package com.dobrica.android.recke.viewActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dobrica.android.recke.R;
import com.dobrica.android.recke.contractViewPresenter.InfoContract;
import com.dobrica.android.recke.model.ReckeModel;
import com.dobrica.android.recke.presenter.InfoPresenter;

/**
 * Created by DOBAR on 9/21/2017.
 */

public class HistoryInfoTab extends Fragment {

    private InfoContract.InfoPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.history_info_tab, container, false);
        presenter = new InfoPresenter((InfoTabbedActivity) getActivity(), getContext());
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {

            populateListView();
            registerClickCallback();
            Button button = (Button) view.findViewById(R.id.btnDeletaAll);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //place your action here
                    presenter.delete();
                }
            });
        } catch (Exception e) {

            Button button = (Button) view.findViewById(R.id.btnDeletaAll);
            button.setText(R.string.empty);
        }
    }

    private void registerClickCallback() {

        ListView list = (ListView) getActivity().findViewById(R.id.historyListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {

                presenter.openNotes(position);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getActivity())
                        .setMessage(R.string.delete)
                        .setCancelable(false)
                        .setPositiveButton(R.string.da, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                presenter.delete(position);
                            }
                        })
                        .setNegativeButton(R.string.ne, null)
                        .show();
                return true;
            }
        });
    }

    private void populateListView() {
        ArrayAdapter<ReckeModel> adapter = new MyListAdapter();
        ListView list = (ListView) getActivity().findViewById(R.id.historyListView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<ReckeModel> {
        public MyListAdapter() {

            super(getActivity(), R.layout.box_history, presenter.getMyModles());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getActivity().getLayoutInflater().inflate(R.layout.box_history, parent, false);
            }

            // Fill the view
            // Time:
            TextView timeText = (TextView) itemView.findViewById(R.id.time);
            timeText.setText(presenter.getMyModles().get(position).getCurrentTime());

            // Names:
            TextView namesText = (TextView) itemView.findViewById(R.id.names);
            namesText.setText(presenter.getMyModles().get(position).player1.getPlayerName() + " vs " +
                    presenter.getMyModles().get(position).player2.getPlayerName());

            return itemView;
        }
    }
}
