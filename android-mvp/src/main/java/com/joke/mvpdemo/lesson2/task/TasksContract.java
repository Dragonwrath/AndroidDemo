package com.joke.mvpdemo.lesson2.task;

import com.joke.mvpdemo.lesson2.BasePresenter;
import com.joke.mvpdemo.lesson2.BaseView;
import com.joke.mvpdemo.lesson2.data.Task;

import java.util.List;

public interface TasksContract {
    interface View extends BaseView<Presenter> {
        void setLodingIndicator(boolean active);
        void showTasks(List<Task> tasks);
    }

    interface Presenter extends BasePresenter{}
}
