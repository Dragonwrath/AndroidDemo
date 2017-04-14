package com.yang.v4demo;

import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.AtomicFile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Log.e(TAG, "onCreate:\n "+metrics.toString() );
        android.support.v4.util.ArrayMap map = new ArrayMap();
    }


    public void start(View view) {
        startActivity(new Intent(this, SecondActivity.class));
        overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);
    }
}
