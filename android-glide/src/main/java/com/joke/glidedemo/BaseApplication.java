package com.joke.glidedemo;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;


public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        MemorySizeCalculator calculator = new MemorySizeCalculator(this);
//        int bitmapPoolSize = calculator.getBitmapPoolSize();
//        int memoryCacheSize = calculator.getMemoryCacheSize();
//        Log.e("memoryCacheSize = " , memoryCacheSize+"");
//        Log.e("bitmapPoolSize = " , bitmapPoolSize+"");
        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH);
    }
}
