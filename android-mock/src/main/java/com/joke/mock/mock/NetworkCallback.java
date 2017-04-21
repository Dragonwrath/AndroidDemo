package com.joke.mock.mock;


public interface NetworkCallback {
    void onSuccess(Object data);
    void onFailure(int code, String msg);
}
