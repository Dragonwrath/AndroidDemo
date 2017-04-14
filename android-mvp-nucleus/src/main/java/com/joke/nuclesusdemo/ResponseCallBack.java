package com.joke.nuclesusdemo;


import android.os.Bundle;

public interface ResponseCallBack{
    public void onDataSuccess(int action, int from, Bundle bundle);
    public void onDataFailure(int action, String error);
}
