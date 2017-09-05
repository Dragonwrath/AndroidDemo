package com.joke.mvpdemo.welcome;


public class WelcomeManager implements DataSource{

    private final RemoteDataSource mRemote;
    private final LocalDataSource mLocal;

    public WelcomeManager(RemoteDataSource remote, LocalDataSource local) {
        mRemote = remote;
        mLocal = local;
    }
}
