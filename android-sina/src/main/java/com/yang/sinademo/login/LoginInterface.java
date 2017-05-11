package com.yang.sinademo.login;


import android.content.Intent;

public interface LoginInterface<T> {
    void login();
    void callback(int requestCode, int resultCode, Intent data );
    void refresh();
    void onSuccess(T t);
    void onFailure(Throwable t);
}
