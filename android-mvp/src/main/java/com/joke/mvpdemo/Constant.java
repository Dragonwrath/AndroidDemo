package com.joke.mvpdemo;


import android.support.annotation.IntDef;

public final class Constant {
    public final static int WIFI = 0 ;
    public final static int MOBILE = 1;
    public final static int NO_NET = -1;

    @IntDef(value = {WIFI,MOBILE,NO_NET})
    public @interface NetType{}
}
