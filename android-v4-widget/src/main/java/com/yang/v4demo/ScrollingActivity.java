package com.yang.v4demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingActivity extends AppCompatActivity implements View.OnAttachStateChangeListener, View.OnLayoutChangeListener {

    private static final String TAG = "ScrollingActivity";
//    @BindView(R.id.toolbar) Toolbar mToolbar;
//    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout mToolbarLayout;
//    @BindView(R.id.app_bar) AppBarLayout mAppBar;
//    @BindView(R.id.fab) FloatingActionButton mFab;

    private ImageView mImageView;
    private NestedScrollView mScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        mImageView = (ImageView) findViewById(R.id.image);
//
//        mToolbarLayout.addOnLayoutChangeListener(this);
//        mToolbarLayout.addOnAttachStateChangeListener(this);

        reSize();

    }

    public void reSize() {
        mImageView = (ImageView) findViewById(R.id.image) ;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.duel, options);
        int scale = (int) Math.ceil(Math.max(getResources().getDisplayMetrics().density, 4));
        options.inJustDecodeBounds = false;
        options.inSampleSize = scale;
        //API21 之后已过时，可以帮助API 11-20 优化内存效果，具体看doc说明
        options.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.duel, options);
        Drawable drawable = mImageView.getBackground();
        gcDrawable(drawable);
        drawable = mImageView.getDrawable();
        gcDrawable(drawable);
        System.gc();

        int byteCount = bitmap.getByteCount();
        Log.e(TAG, "Step 2--------->" + byteCount);
//        mImageView.setImageResource(0);
        mImageView.setImageBitmap(bitmap);
//        mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        mImageView.invalidate();
    }

    private void gcDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bit = (BitmapDrawable) drawable;
            Bitmap bitmap1 = bit.getBitmap();
            int byteCount = bitmap1.getByteCount();
            Log.e(TAG, "Step 1--------->" + byteCount);
            mImageView.setDrawingCacheEnabled(false);
            mImageView.setImageResource(0);
            mImageView.setBackgroundResource(0);
            bit.setCallback(null);
//            mImageView.setImageBitmap(null);
            bitmap1.recycle();
            System.gc();
        }
    }

    @Override
    public void onViewAttachedToWindow(View v) {
        Log.d(TAG, "onViewAttachedToWindow:--->"+v.getClass().getName());
    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        Log.d(TAG, "onViewDetachedFromWindow:--->"+v.getClass().getName());
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        Log.d(TAG, "v:--->"+v.getClass().getName());
    }
}
