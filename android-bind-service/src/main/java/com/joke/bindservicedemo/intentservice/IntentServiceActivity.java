package com.joke.bindservicedemo.intentservice;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.joke.bindservicedemo.R;

public class IntentServiceActivity extends AppCompatActivity {

    private View mLayout;
    private WindowManager mWM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
//        startService(new Intent(this, HelloIntentService.class));
//        showToast();
        showCustomToast();

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideCustomToast();
            }
        },3000);
    }


    private void hideCustomToast() {
        mWM.removeViewImmediate(mLayout);
    }
    private void showCustomToast() {
        mWM = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater inflater = getLayoutInflater();
        mLayout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) mLayout.findViewById(R.id.text);
        text.setText("This is a custom toast");
        WindowManager.LayoutParams params = generateWindowParams();
        mWM.addView(mLayout,params);
    }

    private WindowManager.LayoutParams generateWindowParams(){
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.format = PixelFormat.TRANSLUCENT;
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.setTitle("Toast");
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.verticalWeight = 1.0f;
        return params;
    }


    private void showToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("This is a custom toast");
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
