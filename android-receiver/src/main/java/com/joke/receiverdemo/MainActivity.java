package com.joke.receiverdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DynamicReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String encode = Base64Utils.encode(TAG.getBytes());
        String decodeStr = new String(Base64Utils.decode(encode));
        byte[] encodeString = Base64.encode(TAG.getBytes(), Base64.DEFAULT);
        String decodeString = new String(Base64.decode(encodeString, Base64.DEFAULT));
        Log.e(TAG, "Base64Utils--------->" + decodeStr );
        Log.e(TAG, "Base64     --------->" + decodeString );
//        mReceiver = new DynamicReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(Intent.ACTION_SCREEN_OFF);
//        filter.addAction(Intent.ACTION_SCREEN_ON);
//        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(mReceiver);
    }
}
