package com.joke.widget;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.joke.widget.relativelayout.RelativeLayoutActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getTopForResume();
    }

    private void getTopForResume() {
        ActivityManager service = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = service.getRunningAppProcesses();
        if (runningAppProcesses != null)
        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {

            Log.e(TAG, "ActivityManager.RunningAppProcessInfo: ----->" +runningAppProcess.processName );
        }

//        List<ActivityManager.RunningServiceInfo> runningServices = service.getRunningServices(Integer.MAX_VALUE);
//        if (runningServices != null)
//        for (ActivityManager.RunningServiceInfo runningService : runningServices) {
//            Log.e(TAG, "ActivityManager.RunningServiceInfo : ----->" +runningService.service.getShortClassName());
//        }
    }
}
