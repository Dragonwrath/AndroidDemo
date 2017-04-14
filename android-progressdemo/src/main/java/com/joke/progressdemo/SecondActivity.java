package com.joke.progressdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Interpolator;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SecondActivity extends AppCompatActivity {

    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();
    private ImageView mImageView;
    private Drawable mDrawable;

    private static final String TAG = "SecondActivity";
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_second);

        mProgress = (ProgressBar) findViewById(android.R.id.progress);
//        mImageView = (ImageView) findViewById(R.id.view);


    }

    private int doWork() {
        return mProgressStatus +5;
    }

    boolean expand = true;
    public void start(View view) {
//        mDrawable = mImageView.getDrawable();
//        ClipDrawable drawable = (ClipDrawable)mDrawable;
//        int level = drawable.getLevel();
//        if (expand) {
//            if (level >= 10000){
//                expand = false;
//                drawable.setLevel( level - 1000);
//            } else
//                drawable.setLevel( level + 1000);
//        } else {
//            if (level < 1000 ){
//                expand  = true;
//                drawable.setLevel(level + 1000);
//            } else
//                drawable.setLevel(level - 1000);
//
//        }

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus = doWork();

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void scale(View view) {
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(0.2f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(view, pvhRotation);
        rotationAnim.setDuration(5000);
        rotationAnim.start();
//        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "alpha",0f);
//        anim.setDuration(2000);
//        anim.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                Log.e(TAG,"--->动画开始了");
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Log.e(TAG,"--->动画结束了");
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        anim.start();

    }
}
