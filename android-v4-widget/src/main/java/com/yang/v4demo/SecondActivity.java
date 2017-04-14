package com.yang.v4demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    private float lastX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getRawX();
                float delayX = x- lastX;
                float density = getResources().getDisplayMetrics().density;
                if (delayX > 100 * density) {
                    Log.e(TAG, "Big onTouchEvent: -------->"+delayX);
                    this.finish();
                    overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);

                    return true;
                }
                Log.e(TAG, "Small onTouchEvent: -------->"+delayX);

                break;
        }
        return super.onTouchEvent(event);
    }
}
