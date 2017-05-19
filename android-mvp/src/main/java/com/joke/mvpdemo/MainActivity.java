package com.joke.mvpdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity------->";

@RequiresApi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23){
            int i = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            if (i == PackageManager.PERMISSION_GRANTED) {
                getMyUUID();
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},12);
            }
        } else {
            getMyUUID();
        }
    }

    @SuppressLint("unchecked")
    @SuppressWarnings("unchecked")
    private String getMyUUID(){
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        String tmDevice, tmSerial, tmPhone, androidId;

        tmDevice = tm.getDeviceId();

        Log.e(TAG, "getDeviceId: "+tmDevice );
        tmSerial = tm.getSimSerialNumber() ;
        if (tmSerial == null) {
            tmSerial = "0";
        }
        Log.e(TAG, "getSimSerialNumber: "+tmSerial );


        androidId = android.provider.Settings.Secure.getString(getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
        Log.e(TAG, "ANDROID_ID: "+androidId );

        Log.e(TAG, "ANDROID_ID hashCode(): "+androidId.hashCode() );

        UUID deviceUuid = new UUID(Long.parseLong(androidId,16), (Long.parseLong(tmDevice,16) << 32) | Long.parseLong(tmSerial,16));
        String uniqueId = deviceUuid.toString();
        Log.e(TAG,"uuid= "+uniqueId);
        return uniqueId;
    }
    //Java 获取可变UUID
    private String getRandomUUID(){
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        Log.d("debug","----->UUID"+uuid);
        return uniqueId;
    }

    private String getUUid() {
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        String simSerialNumber = tm.getSimSerialNumber();
        String deviceId = tm.getDeviceId();
        return null;
    }
}
