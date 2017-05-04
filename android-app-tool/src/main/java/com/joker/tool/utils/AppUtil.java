package com.joker.tool.utils;


import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppUtil {
    private AppUtil() {
        throw new AssertionError();
    }

    public static int getVersionCode(Activity act) {
        PackageManager packageManager = act.getPackageManager();
        String packageName = act.getPackageName();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
