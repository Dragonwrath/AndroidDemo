package com.joke.nuclesusdemo.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.joke.nuclesusdemo.ResponseCallBack;


public class BaseModel {
    protected Context context;
    protected ResponseCallBack callBack;

    public BaseModel(Context context,ResponseCallBack callBack) {
        if (context == null)
            return;
        this.context = context;
        this.callBack = callBack;
    }
}
