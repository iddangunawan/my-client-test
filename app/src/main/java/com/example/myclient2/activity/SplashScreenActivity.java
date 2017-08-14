package com.example.myclient2.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.myclient2.R;
import com.example.myclient2.helper.IntentHandler;

public class SplashScreenActivity extends AppCompatActivity {

    //Set waktu lama splashscreen
    private int splashInterval = 2000;
    private boolean isLogin = false;
    private boolean isRegister = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRegister && isLogin) {
                    IntentHandler.goToActivity(SplashScreenActivity.this, SplashScreenActivity.this, MainActivity.class, true);
                } else if (isRegister) {
                    IntentHandler.goToActivity(SplashScreenActivity.this, SplashScreenActivity.this, LoginActivity.class, true);
                } else {
                    IntentHandler.goToActivity(SplashScreenActivity.this, SplashScreenActivity.this, IntroActivity.class, true);
                }
            }
        }, splashInterval);
    }
}
