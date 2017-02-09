package com.joke.frescodemo;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.request.ImageRequest;

import java.io.File;
import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    MyHandler mHandler;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getView();
        SimpleDraweeView dv = (SimpleDraweeView) findViewById(R.id.dv1);
        GenericDraweeHierarchy hierarchy = dv.getHierarchy();
//        DraweeController controller = dv.getController();
//        controller.setHierarchy( hierarchy);
//        RoundingParams roundingParams = new RoundingParams();
//        hierarchy.setProgressBarImage(new ProgressBarDrawable());
        Fresco.newDraweeControllerBuilder()
                .setOldController(dv.getController())
                .build();
    }


    private void getView() {
        SimpleDraweeView dv = (SimpleDraweeView) findViewById(R.id.dv1);
        dv.setHierarchy(
                new GenericDraweeHierarchyBuilder(getResources())
                        .setProgressBarImage(new ProgressBarDrawable())
                        .build());
        mUri = Uri.parse("http://www.th7.cn/d/file/p/2016/04/09/4ebfcf41e600e9caed188f9ec7527732.jpg");
        if (isDownloaded(mUri)) {
            ImageRequest imageRequest = ImageRequest.fromUri(mUri);
            CacheKey cacheKey = DefaultCacheKeyFactory.getInstance()
                    .getEncodedCacheKey(imageRequest, this);
            FileCache mainFileCache = ImagePipelineFactory.getInstance()
                    .getMainFileCache();
            BinaryResource resource = mainFileCache.getResource(cacheKey);
            File file = ((FileBinaryResource) resource).getFile();
            if (file != null) {
                Log.i(TAG, "File-------->" + file.getAbsolutePath());
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                if (bitmap != null) {
                    PipelineDraweeControllerBuilder builder = Fresco.newDraweeControllerBuilder();
                    AbstractDraweeController build = builder.setUri(mUri).build();
                    dv.setController(build);
                }
            }
        } else {
            dv.setImageURI(mUri);
        }
        mHandler = new MyHandler(this);
    }

    private boolean isDownloaded(@NonNull  Uri loadUri) {
        ImageRequest imageRequest = ImageRequest.fromUri(loadUri);
        CacheKey cacheKey = DefaultCacheKeyFactory.getInstance()
                .getEncodedCacheKey(imageRequest, this);
        return ImagePipelineFactory.getInstance().getMainFileCache().hasKey(cacheKey);
    }


    private static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;
        private static final int FINISH = 100;


        MyHandler(Activity activity) {
            mActivityReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case FINISH:
                    break;
            }
        }
    }

    private void test(){
    }
}