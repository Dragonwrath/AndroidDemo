package com.yang.sinademo.sina;


public interface LoginInterface<T> {
    void onSuccess(T t);
    void onFailure(Throwable t);
}
