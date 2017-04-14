package com.apache.volley;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.net.http.AndroidHttpClient;


import com.apache.volley.network.Network;
import com.apache.volley.queue.RequestQueue;
import com.apache.volley.toolbox.base.cache.DiskBasedCache;
import com.apache.volley.toolbox.base.network.BasicNetwork;
import com.apache.volley.toolbox.base.stack.HttpClientStack;
import com.apache.volley.toolbox.base.stack.HttpStack;
import com.apache.volley.toolbox.base.stack.HurlStack;

import java.io.File;

public class Volley {
    private static final String DEFAULT_CACHE_DIR = "volley";

    public static RequestQueue newRequestQueue(Context context, HttpStack stack) {
        File cacheDir = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);

        String userAgent = "volley/0";

        try {
            String packageName = context.getPackageName();
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            userAgent = packageName + "/" + info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        if (stack == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
                stack = new HurlStack();
            } else {
                //Prior to Gingerbread, HttpUrlConnection was unreliable.
                stack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent));
            }
        }

        Network network = new BasicNetwork(stack);

        RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir), network);
        queue.start();

        return queue;
    }

    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, null);
    }



}
