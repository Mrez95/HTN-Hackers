package com.example.mrez.htn_hackers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class SplashScreen extends Activity {
//
//    protected boolean _active = true;
//    protected int _splashTime = 10000; // time to display the splash screen in ms
//
//    /**
//     * Called when the activity is first created.
//     */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);
//
//        // thread for displaying the SplashScreen
//        Thread splashTread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    int waited = 0;
//                    while (_active && (waited < _splashTime)) {
//                        sleep(100);
//                        if (_active) {
//                            waited += 100;
//                        }
//                    }
//                } catch (InterruptedException e) {
//
//                } finally {
//
//                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
//                    finish();
//                    //stop();
//                }
//            }
//        };
//        splashTread.start();
//    }
//}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread startTimer = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                    Intent loadSplash = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(loadSplash);
                    finish();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        startTimer.start();
    }
}



//import android.os.Handler;
//
//public class SplashScreen extends Activity {
//
//    // Splash screen timer
//    private static int SPLASH_TIME_OUT = 25000;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);
//
//        new Handler().postDelayed(new Runnable() {
//
//            /*
//             * Showing splash screen with a timer. This will be useful when you
//             * want to show case your app logo / company
//             */
//
//            @Override
//            public void run() {
//                // This method will be executed once the timer is over
//                // Start your app main activity
//                Intent i = new Intent(SplashScreen.this, MainActivity.class);
//                startActivity(i);
//
//                // close this activity
//                finish();
//            }
//        }, SPLASH_TIME_OUT);
//    }
//
//}

