package com.example.naomi.foodiesindex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Launch the splash screen layout: splash.xml
        setContentView(R.layout.splash);
        Thread splashThread = new Thread() {

            public void run() {
                try {
                    // Sleep time in milliseconds (3000 = 3sec)
                    sleep(3000);
                }  catch(InterruptedException e) {
                    // Trace the error
                    e.printStackTrace();
                } finally
                {
                    // Launch the MainActivity (log in) class
                    Intent intent = new Intent(SplashScreen.this, Home.class); //supposed to be MainActivity.class
                    startActivity(intent);
                }
            }
        };

        // To start the thread
        splashThread.start();
    }
}

