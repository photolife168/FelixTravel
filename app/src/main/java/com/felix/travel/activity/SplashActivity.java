package com.felix.travel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.felix.travel.R;

/**
 * Created by felixlin on 2016/12/28.
 */
public class SplashActivity extends AppCompatActivity {

    private ImageView mSplashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                Intent intent = new Intent(SplashActivity.this, TutorialActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }

    private void init(Bundle savedInstanceState){
        initview();
    }

    private void initview(){
        mSplashView = (ImageView) findViewById(R.id.iv_activity_splash);
        //mSplashView.setImageResource(R.drawable.splash_example);
    }
}
