package com.joker.tool.utils;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;

//可以通过代码设置
//可以给每个View设置相应的SystemUIVisibility呈现不同的方式
//具体可以
public class WindowUtils {
    private WindowUtils() {
        throw new AssertionError();
    }

    @RequiresApi(16)
    public static void fullScreen(Activity activity) {
        View decorView= activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //full sreen
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    @RequiresApi(16)
    public static void restoreDefault(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
    }
}
