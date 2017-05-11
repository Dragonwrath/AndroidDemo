package com.joke.dragonwrathsms;


import android.app.Application;

import cn.smssdk.SMSSDK;

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SMSSDK.initSDK(this, "1c1bc6a43e90c", "859da93cacf82c68f7a285e5c1e24c88");
    }
}
