package com.joke.nuclesusdemo;


import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.Map;

import nucleus.presenter.RxPresenter;

public class MainPresenter extends RxPresenter<MainActivity> {

    public void changeText(Map<String, String> map){
        MainModel model = new WeakReference<>(new MainModel(getView(),getView())).get();
        model.queryRemoteData();
    }
}
