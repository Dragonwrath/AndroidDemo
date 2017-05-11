package com.yang.sinademo;


import com.yang.sinademo.login.SinaUser;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface UserApi {
    @GET("show.json")
    Observable<SinaUser> getUser(@QueryMap HashMap<String,String> map);
}
