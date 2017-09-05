package com.joke.mvpdemo.welcome;


public class RemoteDataSource implements DataSource {
    public static RemoteDataSource INSTANCE;

    private RemoteDataSource() {
    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }
}
