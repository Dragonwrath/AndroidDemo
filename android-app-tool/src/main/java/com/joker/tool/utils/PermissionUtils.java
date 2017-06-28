package com.joker.tool.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//参考网易新闻逻辑
public final class PermissionUtils {

    private static Lock mLock = new ReentrantLock();
    private PermissionUtils() {
        throw new AssertionError("This class can not be instantiated");
    }

    public static void requestPermissionForFirstTime(Context context, int requestCode ,String[] permissions) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            SharedPreferences config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
            //TODO 是否需要外部判断
            int versionCode = config.getInt("versionCode", -1);
            if (versionCode != info.versionCode) { //判断版本号是否相同，确定是否是新版本首次进入
                requestPermission(context, requestCode, permissions);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void requestPermission(Context context, int requestCode ,String[] permissions) {
        //存在并发修改问题，使用BlockingQueue保证迭代修改正确性，
        // 初始化的size，应该根据需要申请的权限数量修改
        ArrayBlockingQueue<String> list = new ArrayBlockingQueue<>(10);
        list.addAll(Arrays.asList(permissions));
        for (String permission : list) {
            if (ActivityCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED) {
                list.remove(permission);
            }
        }
        if (list.size() >= 1) {
            String[] array = new String[list.size()];
            list.toArray(array);
            ActivityCompat.requestPermissions((Activity) context, array, requestCode);
        }
    }

    public static void openSettingForPermission(Context context, int requestCode) {
        Activity activity = (Activity) context;
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        ActivityCompat.startActivityForResult(activity, intent, requestCode, null);

    }


}
