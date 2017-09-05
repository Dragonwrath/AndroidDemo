package com.joke.mvpdemo.welcome;


public class LocalDataSource implements DataSource {
    public static LocalDataSource INSTANCE;

    private LocalDataSource() {
    }

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource();
        }
        return INSTANCE;
    }
}
