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
import android.support.v4.app.Fragment;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

//TODO 需要根据APP的详细逻辑设计进行相应的权限判断，该类不是通用方法，仅仅只提供参考
public final class PermissionUtils {

    private PermissionUtils() {
        throw new AssertionError("This class can not be instantiated");
    }

    public static void requestPermissionForFirstTime(Activity activity, int requestCode ,String[] permissions) {
        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            SharedPreferences config = activity.getSharedPreferences("config", Context.MODE_PRIVATE);
            //TODO 是否需要外部判断
            int versionCode = config.getInt("versionCode", -1);
            if (versionCode != info.versionCode) { //判断版本号是否相同，确定是否是新版本首次进入
                requestPermission(activity, requestCode, permissions);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void requestPermission(Activity activity, int requestCode ,String[] permissions) {
        //存在并发修改问题，使用BlockingQueue保证迭代修改正确性，
        //初始化的size，应该根据需要申请的权限数量修改
        ArrayBlockingQueue<String> list = new ArrayBlockingQueue<>(10);
        list.addAll(Arrays.asList(permissions));
        for (String permission : list) {
            if (ActivityCompat.checkSelfPermission(activity,permission) == PackageManager.PERMISSION_GRANTED) {
                list.remove(permission);
            }
        }
        if (list.size() >= 1) {
            String[] array = new String[list.size()];
            list.toArray(array);
            ActivityCompat.requestPermissions(activity, array, requestCode);
        }
    }

    public static void openSettingForPermission(Activity activity, int requestCode) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        ActivityCompat.startActivityForResult(activity, intent, requestCode, null);
    }

    public static void requestPermission(Fragment fragment, int requestCode, String[] permissions) {
        Context context = fragment.getContext();
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
            fragment.requestPermissions(array,requestCode);
        }
    }

    public static void openSettingForPermission(Fragment fragment, int requestCode) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package",fragment.getContext().getPackageName(),null));
        fragment.startActivityForResult(intent, requestCode);
    }

}