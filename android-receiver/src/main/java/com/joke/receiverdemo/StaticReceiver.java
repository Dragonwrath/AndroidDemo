package com.joke.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class StaticReceiver extends BroadcastReceiver {
    private static final String TAG = "StaticReceiver";
    public StaticReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){
            ConnectivityManager service = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo active = service.getActiveNetworkInfo();
            if (active != null) {
                if (active.getType() == ConnectivityManager.TYPE_WIFI && active.isConnected()) {
                    String s = active.toString();
                    Log.e(TAG, s);
                }
            }
        }
    }
}
