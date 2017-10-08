package com.dobrica.android.recke.viewActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ToggleButton;

import com.dobrica.android.recke.R;
import com.dobrica.android.recke.contractViewPresenter.MainContract;
import com.dobrica.android.recke.presenter.MainPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements MainContract.Main {

    @BindView(R.id.btnNewGame)
    public Button btnNewGame;
    @BindView(R.id.btnInfo)
    public Button btnInfo;
    @BindView(R.id.mutte)
    public ToggleButton btnMutte;
    @BindView(R.id.btnLanguage)
    public Button btnLanguage;

    private MainContract.MainPresenter presenter;
    private String languageToLoad = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        changeLanguage();

        presenter = new MainPresenter(this, getApplicationContext());
        presenter.checkSound();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit app?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);//
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void startInputNamesActivity() {
        startActivity(new Intent(MainActivity.this, InputNamesActivity.class));
    }

    @Override
    public void startInfoActivity() {
        startActivity(new Intent(MainActivity.this, InfoTabbedActivity.class));
    }

    @Override
    public void callOnResume() {
        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        changeLanguage();

        ButterKnife.bind(this);
        languageSetings();
        presenter.checkSound();
    }

    @Override
    public void soundDecisions(Boolean isChecked) {
        if (isChecked) {
            btnMutte.setChecked(true);
            presenter.soundOff(true);
            btnMutte.setBackgroundResource(R.drawable.soundoff);
        } else {
            presenter.soundOff(false);
            btnMutte.setBackgroundResource(R.drawable.soundon);
        }
    }

    @Override
    public void setLanguageToLoad(String languageToLoad) {
        this.languageToLoad = languageToLoad;
    }

    private void languageSetings() {
        if (languageToLoad.equals("en")) {
            btnLanguage.setBackgroundResource(R.drawable.en);
        } else {
            btnLanguage.setBackgroundResource(R.drawable.sr);
        }
    }

    private void changeLanguage() {
        setContentView(R.layout.activity_main);
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    //*************OnClick********************

    @OnClick(R.id.btnNewGame)
    public void onBtnNewGameClicked() {
        presenter.onBtnNewGameClicked();
    }

    @OnClick(R.id.btnInfo)
    public void onBtnInfoClicked() {
        presenter.onBtnInfoClicked();
    }

    @OnCheckedChanged(R.id.mutte)
    public void onMutteClicked() {
        soundDecisions(btnMutte.isChecked());
    }

    @OnClick(R.id.btnLanguage)
    public void onLanguageClicked() {
        if (languageToLoad.equals("")) {

            presenter.btnLanguageClicked(true);
        } else {
            presenter.btnLanguageClicked(false);
        }
    }

}

