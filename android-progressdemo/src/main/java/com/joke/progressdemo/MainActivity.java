package com.joke.progressdemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private static final int PROGRESS = 0x1;
    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestPermission();
        detectNetworkForApi23();
//        //确定进度的标题栏进度条
//        requestWindowFeature(Window.FEATURE_PROGRESS);
//        //不确定进度的标题栏进度条
//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
//        setContentView(R.layout.activity_main);
//        mProgress = (ProgressBar) findViewById(R.id.progress_bar);
//        // Start lengthy operation in a background thread
//        new Thread(new Runnable() {
//            public void run() {
//                while (mProgressStatus < 100) {
//                    mProgressStatus++;
//                    // Update the progress bar
//                    mHandler.post(new Runnable() {
//                        public void run() {
//                            mProgress.setProgress(mProgressStatus);
//                        }
//                    });
//                }
//                Toast.makeText(MainActivity.this,"发送完毕",Toast.LENGTH_SHORT).show();
//            }
//        }).start();
    }
    private int detectNetwork() {

        Context ctx = this;
        ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo.State mobile = null;
        if (networkInfo != null) {
            mobile = networkInfo.getState();
        }

        networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo.State wifi = null;
        if (networkInfo != null) {
            wifi = networkInfo.getState();
        }

        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
            return 1;
        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
            return 2;

        return 3;
    }

    private int detectNetworkForApi23(){
         Context context = this;
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = manager.getActiveNetworkInfo();
        if (net == null)
            return -1;
        int type = net.getType();
        NetworkInfo.State state = net.getState();
        switch (type){
            case ConnectivityManager.TYPE_MOBILE:
                if (state.equals(NetworkInfo.State.CONNECTED)) {
                    return 2;
                }
                break;
            case ConnectivityManager.TYPE_WIFI:
                if (state.equals(NetworkInfo.State.CONNECTED)) {
                    return 1;
                }
                break;        }
        return 3;
    }

    private void requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)!= PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE},100);
            }
        }
    }
}
