package com.joke.v7demo.ratingStarDemo;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.AbsSeekBar;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;

import com.joke.v7demo.R;

public class RatingStarDemoActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    AbsSeekBar mAbsSeekBar;
    SeekBar mSeekBar;
    RatingBar mRatingBar;

    AppCompatSeekBar mAppCompatSeekBar;
    AppCompatRatingBar mAppCompatRatingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_star_demo);

    }

    public void start(View view) {
        initView(view);
    }
    private void initView(View view) {
        Drawable drawable = view.getBackground();
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layer = (LayerDrawable) drawable;
            int num = layer.getNumberOfLayers();

            for (int i = 0; i < num; i++) {
                Drawable child = layer.getDrawable(i);

                if (child instanceof StateListDrawable) {
                    StateListDrawable childState = (StateListDrawable)child;
                    childState.setState(new int[]{android.R.attr.state_checked});
                }
            }
        }
    }
}
