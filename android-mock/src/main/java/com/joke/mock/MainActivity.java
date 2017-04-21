package com.joke.mock;

import android.app.Instrumentation;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.joke.mock.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getWindow().getContext().getApplicationContext();
    }
}
