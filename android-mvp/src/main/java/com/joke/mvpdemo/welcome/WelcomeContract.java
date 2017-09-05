package com.joke.mvpdemo.welcome;


import com.joke.mvpdemo.BasePresenter;
import com.joke.mvpdemo.BaseView;

public interface WelcomeContract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

    }
}
