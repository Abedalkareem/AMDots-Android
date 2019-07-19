package com.abedalkareem.amdots;

import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.dots.abedalkareem.amdotsview.AMDots;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AMDots dot = findViewById(R.id.dotsCenter);
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
