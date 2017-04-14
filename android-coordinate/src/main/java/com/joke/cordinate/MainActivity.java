package com.joke.cordinate;

import android.app.IntentService;
import android.app.Service;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CoordinatorLayout mainImpl21 = (CoordinatorLayout) findViewById(R.id.activity_main);
        }
    }
}
