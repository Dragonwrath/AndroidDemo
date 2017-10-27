package com.apache.volley.dispatcher;


import android.os.Process;

import com.apache.volley.cache.Cache;
import com.apache.volley.delivery.ResponseDelivery;
import com.apache.volley.log.VolleyLog;
import com.apache.volley.request.Request;
import com.apache.volley.response.NetworkResponse;
import com.apache.volley.response.Response;

import java.util.concurrent.BlockingQueue;

public class CacheDispatcher extends Thread {

    private static final boolean DEBUG = VolleyLog.DEBUG;

    private final BlockingQueue<Request<?>> mNetworkQueue;

    private final BlockingQueue<Request<?>> mCacheQueue;

    private final Cache mCache;

    private final ResponseDelivery mDelivery;

    private volatile boolean mQuit = false;

    public CacheDispatcher(
            BlockingQueue<Request<?>> cacheQueue, BlockingQueue<Request<?>> networkQueue,
            Cache cache, ResponseDelivery delivery) {
        mCacheQueue = cacheQueue;
        mNetworkQueue = networkQueue;
        mCache = cache;
        mDelivery = delivery;
    }

    public void quit() {
        mQuit = true;
        interrupt();
    }

    @Override
    public void run() {
        if (DEBUG) VolleyLog.v("start new dispatcher");
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

        mCache.initialize();

        while (true) {
            try {
                final Request<?> request = mCacheQueue.take();
                request.addMarker("cache-queue-take");

                if (request.isCanceled()) {
                    request.finish("cache-discard-canceled");
                    continue;
                }

                Cache.Entry entry = mCache.get(request.getCacheKey());
                if (entry == null) {
                    request.addMarker("cache-miss");
                    // Cache miss; send off to the network dispatcher.
                    mNetworkQueue.put(request);
                    continue;
                }

                if (entry.isExpired()) {
                    request.addMarker("cache-hit-expired");
                    request.setCacheEntry(entry);
                    mNetworkQueue.put(request);
                    continue;
                }

                request.addMarker("cache-hit");
                Response<?> response = request.parseNetworkResponse(
                        new NetworkResponse(entry.data, entry.responseHeaders));
                request.addMarker("cache-hit-parsed");

                if (!entry.refreshNeeded()) {
                    // Completely unexpired cache hit. Just deliver the response.
                    mDelivery.postResponse(request, response);
                }
                else {
                    request.addMarker("cache-hit-refresh-needed");
                    request.setCacheEntry(entry);
                    response.intermediate = true;
                    mDelivery.postResponse(request, response, new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mNetworkQueue.put(request);
                            } catch (InterruptedException e) {
                                // Not much we can do about this.
                            }
                        }
                    });
                }
            } catch (InterruptedException e) {
                // We may have been interrupted because it was time to quit.
                if (mQuit) {
                    return;
                }
                continue;
            }
        }
    }
}
