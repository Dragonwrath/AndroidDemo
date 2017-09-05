package com.joke.mvpdemo.welcome;


import com.joke.mvpdemo.BaseView;

import static com.google.common.base.Preconditions.checkNotNull;

public class WelcomePresenter implements WelcomeContract.Presenter {

    private final WelcomeManager mManager;
    private final WelcomeContract.View mView;

    public WelcomePresenter(WelcomeManager manager, WelcomeContract.View view) {
        mManager = manager;
        mView = checkNotNull(view);
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
