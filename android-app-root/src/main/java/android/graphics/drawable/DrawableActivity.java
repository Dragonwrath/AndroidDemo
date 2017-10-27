package android.graphics.drawable;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.marshon.approotdemo.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

@SuppressLint("NewApi")
public class DrawableActivity extends AppCompatActivity implements Animator.AnimatorListener {

    public Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int centerX = mButton.getWidth() / 2;
                int centerY = mButton.getHeight() / 2;
                int startRadius = mButton.getWidth() / 2;
                int endRadius = 0;
                Animator delayAnimator  = ViewAnimationUtils.createCircularReveal(mButton,
                        centerX,
                        centerY,
                        startRadius,
                        endRadius);
                delayAnimator.setDuration(3000);
                delayAnimator.addListener(DrawableActivity.this);
                delayAnimator.setInterpolator(new LinearInterpolator());
                delayAnimator.start();
            }
        });

    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mButton.setVisibility(View.GONE);
        mButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                mButton.setVisibility(View.VISIBLE);
            }
        },3000);
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
