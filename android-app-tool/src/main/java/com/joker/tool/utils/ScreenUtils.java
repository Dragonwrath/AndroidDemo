package com.joker.tool.utils;


import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Method;

public class ScreenUtils {

    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;

    private static int STATUS_BAR_HEIGHT;
    private static int VISUAL_BAR_HEIGHT;

    //获取屏幕宽度
    public static int getScreenWidth(Context context) {
        if (SCREEN_WIDTH == 0) {
            WindowManager systemService = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metric = new DisplayMetrics();
            systemService.getDefaultDisplay().getMetrics(metric);
            SCREEN_WIDTH = metric.widthPixels;
        }
        return SCREEN_WIDTH;
    }

    //获取屏幕高度
    public static int getScreenHeight(Context context) {
        if (SCREEN_HEIGHT == 0) {
            WindowManager systemService = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metric = new DisplayMetrics();
            systemService.getDefaultDisplay().getMetrics(metric);
            SCREEN_HEIGHT = metric.heightPixels;
        }
        return SCREEN_HEIGHT;
    }

    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        if (STATUS_BAR_HEIGHT == 0) {
            Resources resource = context.getResources();
            int resourceId = resource.getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                STATUS_BAR_HEIGHT = resource.getDimensionPixelSize(resourceId);
            }
        }
        return STATUS_BAR_HEIGHT;
    }

    //获取底部虚拟按键栏高度
    public static int getVisualBarHeight(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        if (VISUAL_BAR_HEIGHT == 0) {
            WindowManager systemService = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metric = new DisplayMetrics();
            DisplayMetrics realMetric = new DisplayMetrics();
            Display display = systemService.getDefaultDisplay();
            display.getMetrics(metric);
            display.getRealMetrics(realMetric);
            return VISUAL_BAR_HEIGHT = realMetric.heightPixels - metric.heightPixels;
        }
        return VISUAL_BAR_HEIGHT;
    }
}
