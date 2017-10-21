package com.joker.tool.handler;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

/**
 * handler factory that is used to produce the special feature the subclass of Handler {@link Handler}
 * we have two ways to use
 * 1、if you want to use handler in background，you should use newHandler method
 *  which is animated by {@link WorkerThread}
 * 2、if you want to use handler in MainThread，you should use newInstance method
 *  which is animated by {@link UiThread}
 *  Notice : the messenger's action is different between that two method,
 *   {@link UiThread} shows you that action must work in MainThread
 *   {@link WorkerThread} shows you that action can not work in MainThread
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class HandlerFactory {

    private final static ConcurrentHashMap<String, HandlerThread> MAP = new ConcurrentHashMap<>();

    @WorkerThread
    public static Handler newHandler(final MessageConsumer messageConsumer, String key) {
        HandlerThread thread = MAP.get(key);

        if (thread == null) {
            thread = new HandlerThread(key);
            MAP.putIfAbsent(key, thread);
        }

        synchronized (thread) {
            if (!thread.isAlive()) {
                thread.start();
            }
        }

        return new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if ( messageConsumer != null)
                    messageConsumer.handleMessage(msg);
            }
        };
    }

    @UiThread
    public static Handler newInstance(Activity activity, MessageConsumer messageConsumer) {
        return new WeakReferenceHandler(activity, messageConsumer);
    }

    public static void terminateHandler(String key) {
        final HandlerThread thread = MAP.get(key);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            thread.quitSafely();
        } else {
            thread.quit();
        }
        //Notice： we MUST interrupt this thread， otherwise, when we use this method in
        //Activity.onStop() and then make a new instance by HandlerFactory.newHandler()
        //thread maybe have wrong state (the thread still running, and the looper of
        // thread has stopped, and then an IllegalThreadStateException will be thrown)
        thread.interrupt();

        MAP.remove(key);
    }

    private static class WeakReferenceHandler extends Handler {
        private WeakReference<Activity> reference ;
        private MessageConsumer mMessageConsumer;
        private WeakReferenceHandler(Activity act,MessageConsumer messageConsumer) {
            reference = new WeakReference<>(act);
            this.mMessageConsumer = messageConsumer;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mMessageConsumer != null) mMessageConsumer.handleMessage(msg);
        }
    }

    static abstract class MessageConsumer {
        abstract void handleMessage(Message msg);
    }

}