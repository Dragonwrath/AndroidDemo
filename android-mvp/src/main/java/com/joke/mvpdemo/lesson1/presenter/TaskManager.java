package com.joke.mvpdemo.lesson1.presenter;

import com.joke.mvpdemo.lesson1.model.TaskDataSource;

public class TaskManager {
    TaskDataSource dataSource;

    public TaskManager(TaskDataSource dataSource) {
        this.dataSource = dataSource;
    }
    public String getShowContent() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dataSource.getStringFromRemote() + dataSource.getStringFromCache();
    }
}
