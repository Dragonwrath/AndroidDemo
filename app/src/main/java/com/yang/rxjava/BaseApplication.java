package com.yang.rxjava;

import android.app.Application;

import com.yang.rxjava.db.DBManager;

/**
 * Created by JunWei on 2017/1/4.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    private void initDB() {
        DBManager.getInstance(this).init();
    }
}
