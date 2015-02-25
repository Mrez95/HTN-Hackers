package com.example.mrez.htn_hackers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread startTimer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    Intent loadSplash = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(loadSplash);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        startTimer.start();
    }
}