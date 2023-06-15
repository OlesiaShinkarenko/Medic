package com.example.medic.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medic.Onboard.OnBoardActivity;
import com.example.medic.R;

public class SplashScreen extends AppCompatActivity {
   private Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slpash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                   i = new Intent(SplashScreen.this, OnBoardActivity.class);

                startActivity(i);
                finish();

            }
        },2000);

    }
}