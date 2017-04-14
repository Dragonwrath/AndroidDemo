package com.joke.mvpdemo.lesson1.presenter;

import com.joke.mvpdemo.lesson1.model.TaskDataSourceImpl;
import com.joke.mvpdemo.lesson1.view.MainView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainPresenter {
    private MainView mainView;
    private TaskManager taskManager;

    public MainPresenter() {
        this.taskManager = new TaskManager(new TaskDataSourceImpl());
    }

    public MainPresenter addTaskListener(MainView viewListener) {
        this.mainView = viewListener;
        return this;
    }

    public void getString() {
        Func1<String, String> func1 = new Func1<String, String>() {
            @Override
            public String call(String s) {
                return taskManager.getShowContent();
            }
        };
        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                mainView.onShowString(s);
            }
        };

        Action1<Throwable> throwableAction1 = new Action1<Throwable>() {
            @Override
            public void call(Throwable t) {
                mainView.onFailureString(t.getMessage());
            }
        };
        Observable.just("")
                .map(func1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1,throwableAction1);
    }
}
