package com.joker.tool;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class ImageActivity extends AppCompatActivity {

    private static final String TAG = "ImageActivity";
    private ImageReceiver mReceiver;

    @RequiresApi(23)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        filter.addAction(Camera.ACTION_NEW_PICTURE);
        mReceiver = new ImageReceiver();
        registerReceiver(mReceiver,filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    class ImageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)) {
                Log.e(TAG, "onReceive: " + intent.getData().toString() );
            }
            if (action.equals(Camera.ACTION_NEW_PICTURE)) {
                Log.e(TAG, "onReceive: " + intent.getData().toString() );
            }
        }
    }
}
