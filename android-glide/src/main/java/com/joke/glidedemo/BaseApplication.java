package com.joke.glidedemo;

import android.app.Application;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;


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
