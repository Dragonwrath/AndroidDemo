package com.joke.mock.mock;

public class ApiService {
    private Callback callback;
    public Callback login(String string) {
        callback.onSucess(string);
        return callback;
    }

    public void setCallback(Callback callback){
        this.callback =callback;
    }
}
interface Callback {
    void onSucess(String string);
}
