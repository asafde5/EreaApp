package com.example.ereaapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ereaapp.DB.CountriesDBHelper;
import com.example.ereaapp.R;
import com.example.ereaapp.DB.ServerCountriesList;
import com.example.ereaapp.UI.Listenres.CountriesDBListener;

public class SplashActivity extends AppCompatActivity implements CountriesDBListener {

    private ServerCountriesList getCountries;
    private CountriesDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        helper = new CountriesDBHelper(this);
        getCountries = new ServerCountriesList(helper, this, this);
        getCountriesList();
    }

    public void moveToCountriesActivity(){
        Intent i = new Intent(SplashActivity.this, CountriesActivity.class);
        startActivity(i);
        finish();
    }

    public void getCountriesList(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(helper.getCountries(1).isEmpty()) {
                    getCountries.GetDataFromWebSite();
                }else{
                    moveToCountriesActivity();
                }
            }
        }, 3000);
    }

    @Override
    public void getCountriesListSuccess() {
        moveToCountriesActivity();
    }

    @Override
    public void getCountriesListFailure() {
        //Toast
    }


}
