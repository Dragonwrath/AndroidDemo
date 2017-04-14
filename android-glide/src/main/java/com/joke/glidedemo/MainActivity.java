package com.joke.glidedemo;

import android.app.Notification;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.joke.glidedemo.palette.PaletteAdapter;

import java.util.Arrays;

import static android.R.attr.bitmap;

public class MainActivity extends AppCompatActivity {
      String yourUrl ;
//    ImageView yourImageView = new ImageView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/**
        int maxSize = Glide.get(this).getBitmapPool().getMaxSize();
        int myWidth = 512;
        int myHeight = 384;
        Log.e("maxSize =",  maxSize+"");

        Glide.with(this).load(Path.eatFoodyImages[0])
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(myWidth, myHeight) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        // Do something with bitmap here.
                    }
                });


        Glide.with(this)
                .load(yourUrl)
                .asBitmap()
                .into(new BitmapImageViewTarget(yourImageView) {
                @Override
                public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                    super.onResourceReady(bitmap, anim);
                    Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            // Here's your generated palette
                        }
                    });
            }
        });
**/
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_main);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(
//                new PaletteAdapter(
//                        getApplicationContext(),
//                        Glide.with(this),
//                        Arrays.asList(Path.url)));
//
//        Glide.with(getApplicationContext())
//                .load(Path.eatFoodyImages[0])
////                .thumbnail(0.1f)
//                .into((ImageView)findViewById(R.id.image_main))
//        ;
//        DrawableRequestBuilder<String> thumbnail = Glide.with(getApplicationContext())
//                .load(Path.eatFoodyImages[0])
//                .thumbnail(0.2f);
//        Target<GlideDrawable> into = thumbnail.into((ImageView) findViewById(R.id.image_second));

        loadNotification();
    }


    private void loadNotification(){

        final RemoteViews rv = new RemoteViews(getPackageName(), R.layout.list_item);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Content Title")
                        .setContentText("Content Text")
                        .setContent(rv)
                        .setPriority( NotificationCompat.PRIORITY_MIN);
        final Notification notification = mBuilder.build();

        NotificationTarget target = new NotificationTarget(
                MainActivity.this,
                rv,
                R.id.image_item,
                notification,
                100);
        Glide.with(getApplicationContext())
                .load(Path.eatFoodyImages[0])
                .asBitmap()
                .into(target);


    }
}
