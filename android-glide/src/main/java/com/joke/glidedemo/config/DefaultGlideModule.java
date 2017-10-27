package com.joke.glidedemo.config;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;


public class DefaultGlideModule implements GlideModule {



    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
        //100M default values is 250M
        final int cacheSize = 100 <<11;
        //set default directory  which default values is data/data/packageName/caches/directoryName
        //if you should change this directory,Must define a new class which extends
        // com.bumptech.glide.load.engine.cache.DiskLruCacheFactory ,or,
        final String cacheDirectory = "glide" ;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,cacheDirectory,cacheSize));

//        builder.setMemoryCache(new LruResourceCache(yourSizeInBytes));

//        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
//        int bitmapPoolSize = calculator.getBitmapPoolSize();
//        int memoryCacheSize = calculator.getMemoryCacheSize();
//        Log.e("memoryCacheSize = " , memoryCacheSize+"");
//        Log.e("bitmapPoolSize = " , bitmapPoolSize+"");
//        Glide.get(context).setMemoryCategory(MemoryCategory.HIGH);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.

    }
}
