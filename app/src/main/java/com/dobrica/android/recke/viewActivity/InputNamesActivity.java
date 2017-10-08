package com.dobrica.android.recke.viewActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.dobrica.android.recke.R;
import com.dobrica.android.recke.contractViewPresenter.InputNamesContract;
import com.dobrica.android.recke.presenter.InputNamesPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class InputNamesActivity extends AppCompatActivity implements InputNamesContract.InputNames {

    @BindView(R.id.textA)
    public EditText p1NameEntry;
    @BindView(R.id.textB)
    public EditText p2NameEntry;
    @BindView(R.id.btnStart)
    public Button btnInputNames;

    private InputNamesContract.InputNamesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input_names);

        presenter = new InputNamesPresenter(this);

        ButterKnife.bind(this);
    }

    @Override
    public void startNewActivity() {
        startActivity(new Intent(InputNamesActivity.this, NotesActivity.class).putExtra("Tag", "inputNames"));
    }

    @Override
    public String getP1NameEntry() {
        return p1NameEntry.getText().toString();
    }

    @Override
    public String getP2NameEntry() {
        return p2NameEntry.getText().toString();
    }


    @OnClick(R.id.btnStart)
    public void btnStartClicked(View view) {
        presenter.onBtnStart();
    }
}