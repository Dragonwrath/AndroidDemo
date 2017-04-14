package com.apache.volley.toolbox.advance.request;

import com.apache.volley.cache.Cache;
import com.apache.volley.request.Request;
import com.apache.volley.response.NetworkResponse;
import com.apache.volley.response.Response;


import android.os.Handler;
import android.os.Looper;

public class ClearCacheRequest extends Request<Object> {
    private final Cache mCache;
    private final Runnable mCallback;

    public ClearCacheRequest(Cache cache, Runnable callback) {
        super(Method.GET, null,null);
        mCache = cache;
        mCallback = callback;
    }

    @Override
    public boolean isCanceled() {
        mCache.clear();
        if (mCallback != null) {
            Handler handler =new Handler(Looper.getMainLooper());
            handler.postAtFrontOfQueue(mCallback);
        }
        return true;
    }

    @Override
    public Priority getPriority() {
        return Priority.IMMEDIATE;
    }

    @Override
    public Response<Object> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    public void deliverResponse(Object response) {
    }


}
