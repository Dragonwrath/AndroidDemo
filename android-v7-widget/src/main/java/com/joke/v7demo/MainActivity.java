package com.joke.v7demo;

import android.os.Bundle;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleArrayMap map = new SimpleArrayMap();
        SeekBar mSeek;
        test();
    }

    private void test() {
        System.out.println("4"+3+1);
        System.out.println(4+3 + "1");
    }
}
