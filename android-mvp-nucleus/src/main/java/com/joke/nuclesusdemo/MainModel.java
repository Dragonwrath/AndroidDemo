package com.joke.nuclesusdemo;


import android.content.Context;
import android.os.Bundle;

import com.joke.nuclesusdemo.base.BaseModel;

import javax.security.auth.Subject;

import rx.Observable;
import rx.schedulers.Schedulers;


public class MainModel extends BaseModel {
    public MainModel(Context context, ResponseCallBack callBack) {
        super(context, callBack);
    }

    public void queryRemoteData() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        bundle.putString("textView","From Remote Data");
        callBack.onDataSuccess(1,1,bundle);
    }

}
