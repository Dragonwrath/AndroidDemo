package com.joke.frescodemo;

import android.app.Application;

import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
