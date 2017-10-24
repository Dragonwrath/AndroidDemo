package com.joker.tool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class ImageActivity extends AppCompatActivity {

    private static final String TAG = "ImageActivity";
    private ImageReceiver mReceiver;

    @RequiresApi(23)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,10);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        filter.addAction(Camera.ACTION_NEW_PICTURE);
        mReceiver = new ImageReceiver();
        registerReceiver(mReceiver,filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(mReceiver);
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
