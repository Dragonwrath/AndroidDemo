package com.yang.rxjava;

import android.app.Application;
import android.util.ArrayMap;

import com.yang.rxjava.db.DBManager;

import java.util.Arrays;
import java.util.Vector;

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

interface interface1{
    void test1();
}
interface interface2 {
    void test2();
}

interface interfaceT extends interface1,interface2 {
    void test3();
}