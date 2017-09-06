package com.joker.dagger;


import android.app.Activity;
import android.app.Application;

import com.joker.dagger.main.MainActivity;
import com.joker.dagger.model.person.PersonModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class BaseApplication extends Application implements HasActivityInjector {

    @dagger.Component(modules = {MainActivity.Module.class, PersonModule.class})
    public interface Component {
        BaseApplication inject(BaseApplication application);
    }

    @Inject DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerBaseApplication_Component.create().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

}
