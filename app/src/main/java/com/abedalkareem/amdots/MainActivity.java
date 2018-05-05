package com.abedalkareem.amdots;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dots.abedalkareem.amdotsview.AMDots;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AMDots dot = (AMDots) findViewById(R.id.dotsCenter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dot.stop();
            }
        },4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dot.start();
            }
        },7000);
    }
}
