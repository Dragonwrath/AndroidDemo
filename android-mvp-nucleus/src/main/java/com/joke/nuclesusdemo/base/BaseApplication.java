package com.joke.nuclesusdemo.base;


import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BaseApplication extends Application {
    private static BaseApplication instance;

    private final static BlockingQueue<Activity> activityList = new ArrayBlockingQueue<>(32);

    public static BaseApplication getInstance() {
        if (instance == null) {
            synchronized (BaseApplication.class) {
                instance = new BaseApplication();
            }
        }
        return instance;
    }


    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activityList.contains(activity))
            activityList.remove(activity);
    }
}
