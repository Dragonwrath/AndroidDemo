package com.joke.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DynamicReceiver extends BroadcastReceiver {
    private static final String TAG = "DynamicReceiver";
    public DynamicReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SCREEN_OFF)){
            Log.e(TAG, "onReceive:ACTION_SCREEN_OFF");
        } else if (action.equals(Intent.ACTION_SCREEN_ON)) {
            Log.e(TAG, "onReceive:ACTION_SCREEN_ON");
        }
    }
}
