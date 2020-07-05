package com.example.switchinglang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Toolbar myToolbar;
    private Spinner mLanguageSpinner;
    private Button mLanguageBtn;
    private SharedPreferences myLanguage;
    private static String MY_LANG = "my_lang";
    private String mLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myLanguage = getSharedPreferences("myLang", MODE_PRIVATE);
        mLang = myLanguage.getString(MY_LANG, "");
        setConfig(mLang);
        setContentView(R.layout.activity_main);
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        initViews();
    }

    private void initViews() {
        mLanguageSpinner = findViewById(R.id.languageSpinner);
        mLanguageBtn = findViewById(R.id.btnLang);
        spinnerLanguage();
        mLanguageBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setConfig(mLang);
                SharedPreferences.Editor myEditor = myLanguage.edit();
                myEditor.putString(MY_LANG, mLang);
                myEditor.apply();
                recreate();
            }
        });
    }

    private void setConfig(String mLanguage) {
        Locale locale = new Locale(mLanguage);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    private void spinnerLanguage() {
        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(this, R.array.language, R.layout.spiner);
        adapterLanguage.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mLanguageSpinner.setAdapter(adapterLanguage);
        mLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int language = mLanguageSpinner.getSelectedItemPosition();
                languageSelection(language);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void languageSelection(int languages) {
        switch (languages) {
            case 0:
                mLang = "ru";
                break;
            case 1:
                mLang = "en";
                break;
            case 2:
                mLang = "de";
                break;
        }
    }
}