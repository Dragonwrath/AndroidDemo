package com.yang.sinademo;

import android.content.Context;
import android.os.Handler;

import java.lang.ref.WeakReference;

public class SpecialHandler<T> extends Handler {
    private WeakReference<T> mReference;

    public SpecialHandler(T t) {
        mReference = new WeakReference<>(t);
    }
}

