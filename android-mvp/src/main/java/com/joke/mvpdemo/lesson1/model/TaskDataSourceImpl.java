package com.joke.mvpdemo.lesson1.model;


public class TaskDataSourceImpl implements TaskDataSource {
    @Override
    public String getStringFromRemote() {
        return "Data From Remote";
    }

    @Override
    public String getStringFromCache() {
        return "Data From Cache";
    }
}
