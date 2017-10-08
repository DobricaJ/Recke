package com.dobrica.android.recke.viewActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dobrica.android.recke.R;
import com.dobrica.android.recke.contractViewPresenter.ScoreContract;
import com.dobrica.android.recke.presenter.ScorePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;


public class ScoreActivity extends AppCompatActivity implements ScoreContract.Score {

    @BindView(R.id.etScoreP1)
    TextView etScoreP1;
    @BindView(R.id.etScoreP2)
    TextView etScoreP2;

    @BindView(R.id.foraP1)
    CheckBox checkBoxForaP1;
    @BindView(R.id.foraP2)
    CheckBox checkBoxForaP2;

    @BindView(R.id.p1Name)
    TextView p1Name;
    @BindView(R.id.p2Name)
    TextView p2Name;

    @BindView(R.id.btnEnter)
    Button btnEnter;
    @BindView(R.id.btnPat)
    Button btnPat;

    private ScoreContract.ScorePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ButterKnife.bind(this);
        presenter = new ScorePresenter(this, getApplicationContext());
    }

    @Override
    public void displayNames(String playerName1, String playerName2) {

        p1Name.setText(playerName1);
        p2Name.setText(playerName2);
    }

    @Override
    public void displayScore(int p1, int p2) {
        etScoreP1.setText(String.valueOf(p1));
        etScoreP2.setText(String.valueOf(p2));
    }

    @Override
    public void changePatButton(String picName) {
        btnPat.setBackgroundResource(getResources().getIdentifier(picName, "id", getPackageName()));
    }

    @Override
    public int[] getTextEdit() {
        int score[] = {Integer.parseInt(etScoreP1.getText().toString()), Integer.parseInt(etScoreP2.getText().toString())};
        return score;
    }

    @Override
    public void startNewActivity() {
        startActivity(new Intent(ScoreActivity.this, NotesActivity.class).putExtra("Tag", "score"));
    }

    private void scoreBoxVisibilty(TextView v) {
        v.setVisibility(View.INVISIBLE);
    }


    //////****************************OnClick******************************************8

    @OnCheckedChanged(R.id.foraP1)
    public void foraP1() {
        if(checkBoxForaP1.isChecked()) {
        new AlertDialog.Builder(this)
                .setMessage("Igrac "+ p1Name.getText() + " smatra da je pobedio!" +
                        "\nPrebrojati njegove karte i uneti samo njegov rezultat")

                .setCancelable(false)
                .setPositiveButton(R.string.da, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        presenter.foraP1isChanged(true);
                        scoreBoxVisibilty(etScoreP2);
                    }
                })

                .setNeutralButton("Ponisti",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                checkBoxForaP1.setChecked(false);
                            }
                        })
                .show();

    }else{
            Intent intent = getIntent();
            finish();
            startActivity(intent);
    }
        //show dialog box if yes presenter prezenter on button enter show viner
        //if no uncheck and continue
    }

    @OnCheckedChanged(R.id.foraP2)
    public void foraP2() {
        if(checkBoxForaP2.isChecked()) {
            new AlertDialog.Builder(this)
                    .setMessage("Igrac " + p2Name.getText() + " smatra da je pobedio!" +
                            "\nPrebrojati njegove karte i uneti samo njegov rezultat")

                    .setCancelable(false)
                    .setPositiveButton(R.string.da, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            presenter.foraP2isChanged(true);
                            scoreBoxVisibilty(etScoreP1);
                        }
                    })

                    .setNeutralButton("Ponisti",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    checkBoxForaP2.setChecked(false);
                                }
                            })
                    .show();
        }else{
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        //show dialog box if yes presenter prezenter on button enter show viner
        //if no uncheck and continue
    }

    @OnClick(R.id.btnEnter)
    public void onBtEnterClicked(View view) {
        presenter.onBtnEnterClicked();
    }

    @OnClick(R.id.btnPat)
    public void onBtnPatClicked(View view) {
        presenter.onBtnPatClicked();
    }

    @OnClick({R.id.btnAddP1, R.id.btnSubP2})
    public void onBtnAddP1Clicked(View view) {
        presenter.onBtnAddClicked();
    }

    @OnClick({R.id.btnSubP1, R.id.btnAddP2})
    public void onBtnSubP1Clicked(View view) {
        presenter.onBtnSubClicked();
    }

}
