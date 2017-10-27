package com.joker.tool;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.joker.tool.utils.ScreenUtils;
import com.joker.tool.utils.SystemBarTintManager;

import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnSystemUiVisibilityChangeListener {

    private static final String TAG = "MainActivity";
    @BindView(R.id.text1) TextView mText1;
    @BindView(R.id.text2) TextView mText2;
    @BindView(R.id.text3) TextView mText3;
    @BindView(R.id.text4) TextView mText4;

    private Handler mHandler = new WeakHandler(this) {
        @Override
        public void handleMessage(Message msg) {
//            mDecorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_VISIBLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            );
            mText1.setFitsSystemWindows(true);
            mText1.setText(String.format(Locale.getDefault(), "屏幕高度  %d" , ScreenUtils.getScreenHeight(MainActivity.this)));
            mText2.setText(String.format(Locale.getDefault(), "屏幕宽度  %d" , ScreenUtils.getScreenWidth(MainActivity.this)));
            mText3.setText(String.format(Locale.getDefault(), "状态栏高度 %d" , ScreenUtils.getScreenWidth(MainActivity.this)));
            mText4.setText(String.format(Locale.getDefault(), "虚拟栏高度 %d" , ScreenUtils.getScreenWidth(MainActivity.this)));
        }
    } ;
    private View mDecorView;
    private Window mWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

        // 自定义颜色
        tintManager.setTintColor(Color.YELLOW);
        Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.immediate())
//                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.newThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mText1.setText(String.format(Locale.getDefault(), "屏幕高度  %d", ScreenUtils.getScreenHeight(MainActivity.this)));
                        mText2.setText(String.format(Locale.getDefault(), "屏幕宽度  %d", ScreenUtils.getScreenWidth(MainActivity.this)));
                        mText3.setText(String.format(Locale.getDefault(), "状态栏高度 %d", ScreenUtils.getStatusBarHeight(MainActivity.this)));
                        mText4.setText(String.format(Locale.getDefault(), "虚拟栏高度 %d", ScreenUtils.getVisualBarHeight(MainActivity.this)));
                    }
                });
////        StatusBarUtil.setColor(MainActivity.this, Color.YELLOW);
//        mWindow = getWindow();
//        mWindow.setStatusBarColor(Color.TRANSPARENT);
//        mDecorView = mWindow.getDecorView();
//        mDecorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
//        mDecorView.setOnSystemUiVisibilityChangeListener(this);
//        mHandler.sendEmptyMessageDelayed(1, 2000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSystemUiVisibilityChange(int visibility) {
        Log.e(TAG, "onSystemUiVisibilityChange: "+visibility );
    }

    private static class WeakHandler extends Handler {
        WeakReference<Activity> reference;
        WeakHandler(Activity act) {
            this.reference =  new WeakReference<>(act);
        }
    }

}
