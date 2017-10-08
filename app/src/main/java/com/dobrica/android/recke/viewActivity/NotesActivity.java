package com.dobrica.android.recke.viewActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dobrica.android.recke.R;
import com.dobrica.android.recke.contractViewPresenter.NotesContract;
import com.dobrica.android.recke.presenter.NotesPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesActivity extends AppCompatActivity implements NotesContract.Notes{

    @BindView(R.id.p1Name)TextView p1Name;
    @BindView(R.id.p2Name)TextView p2Name;
    @BindView(R.id.totalA)TextView p1FinalScore;
    @BindView(R.id.totalB)TextView p2FinalScore;
    @BindView(R.id.tvScore)TextView tvScore;

    @BindView(R.id.btnPlayer1)LinearLayout btnPlayer1;
    @BindView(R.id.btnPlayer2)LinearLayout btnPlayer2;
    @BindView(R.id.btnScore)LinearLayout btnScore;
    @BindView(R.id.WebView) WebView web;

    private NotesContract.NotesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ButterKnife.bind(this);

        presenter = new NotesPresenter(this,getApplicationContext());

    }
    @Override
    public void onBackPressed() {
        String tag = getIntent().getStringExtra("Tag");
        if(tag.equals("history") ){
            presenter.mpStop();
            super.onBackPressed();
        }else {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.backClick)
                    .setCancelable(false)
                    .setPositiveButton(R.string.da, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            presenter.onExitYes();
                            startActivity(new Intent(NotesActivity.this, MainActivity.class));


                        }
                    })
                    .setNegativeButton(R.string.ne, null)
                    .show();
        }
    }

    @Override
    public void playAnim(){
        web.setVisibility(View.VISIBLE);
        web.setBackgroundColor(0x00000000);
        web.loadUrl("file:///android_asset/anim.gif");
    }

    @Override
    public void disableButtons(){
        btnPlayer1.setEnabled(false);
        btnPlayer2.setEnabled(false);
        tvScore.setEnabled(false);
    }

    @Override
    public void startNewActivity() {
        startActivity(new Intent(NotesActivity.this, ScoreActivity.class));
    }

    @Override
    public void showNames(String playerName1, String playerName2) {
        p1Name.setText(playerName1);
        p2Name.setText(playerName2);
    }

    @Override
    public void showRecke(String[] sss) {

        int aID = getResources().getIdentifier(sss[0], "id", getPackageName());

        ImageView image = ((ImageView) findViewById(aID));
        image.setImageResource(getResources().getIdentifier(sss[1], "id", getPackageName()));
    }

    @Override
    public void showScore(String scoreP1andP2) {
        tvScore.setText(scoreP1andP2);
    }

    @Override
    public void showFinalScore(String p1, String p2) {
        p1FinalScore.setText( p1);
        p2FinalScore.setText( p2);
    }

//**********************OnClick****************

    @OnClick(R.id.btnPlayer1)
    public void onClickPlayer1(View view){
        presenter.onBtnPlayer1Clicked();
    }
    @OnClick(R.id.btnPlayer2)
    public void onClickPlayer2(View view){
        presenter.onBtnPlayer2Clicked();
    }
    @OnClick(R.id.tvScore)
    public void onClickScore(View view){
        presenter.onBtnScoreClicked();
    }


}













