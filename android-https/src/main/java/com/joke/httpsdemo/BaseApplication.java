package com.joke.httpsdemo;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.joke.httpsdemo.config.IConfig;
import com.joke.httpsdemo.config.PreferenceConfig;


public class BaseApplication extends Application {
    public static Context mApplicationContext ;
    private static BaseApplication sInstance;
    public final static int PREFERENCECONFIG = 0;

    private static BaseApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
        mInstance = this;
    }

    public static Context getContext() {
        return mApplicationContext;
    }

    public static BaseApplication getInstance() {
        if (mInstance != null) {
            return mInstance;
        }
        return null;
    }
    public IConfig getConfig(@NonNull int configType) {
        switch (configType) {
            case 0:
                return PreferenceConfig.getPreferenceConfig(this);
            default:
               return null;
        }
    }
}
