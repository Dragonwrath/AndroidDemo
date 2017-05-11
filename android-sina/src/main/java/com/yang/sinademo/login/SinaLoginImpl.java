package com.yang.sinademo.login;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.yang.sinademo.Constants;
import com.yang.sinademo.MainTabActivity;
import com.yang.sinademo.R;
import com.yang.sinademo.UserApi;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SinaLoginImpl implements LoginInterface<SinaUser>{
    private Activity activity;
    private SsoHandler mSsoHandler;

    public SinaLoginImpl(@NonNull Activity activity) {
        this.activity = activity;
        mSsoHandler = new SsoHandler(activity);
    }

    @Override
    public void login() {
        mSsoHandler.authorizeWeb(new SelfWbAuthListener());
    }

    @Override
    public void callback(int requestCode, int resultCode, Intent data) {
        mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
    }

    @Override
    public void refresh() {
        AccessTokenKeeper.refreshToken(Constants.APP_KEY, activity, new RequestListener() {
            @Override
            public void onComplete(String response) {
                Oauth2AccessToken token = new Gson().fromJson(response, Oauth2AccessToken.class);
                if (token != null
                        && !TextUtils.isEmpty(token.getUid())
                        && !TextUtils.isEmpty(token.getToken())){
                    AccessTokenKeeper.writeAccessToken(activity,token);
                    requestUserData(token);
                }
            }

            @Override
            public void onWeiboException(WeiboException e) {
                onFailure(e);
            }
        });
    }

    @Override
    public void onSuccess(SinaUser sinaUser) {
        if (sinaUser != null) {
            long id = sinaUser.getId();
            String url = sinaUser.getProfile_image_url();
            String name = sinaUser.getName();
            if (id > 0 && !TextUtils.isEmpty(url) && !TextUtils.isEmpty(name)){
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                bundle.putString("url",url);
                bundle.putString("id", String.valueOf(id));
                Intent intent = new Intent(activity, MainTabActivity.class);
                intent.putExtras(bundle);
                ContextCompat.startActivity(activity,intent,null);
            }
        }
    }

    @Override
    public void onFailure(Throwable t) {
        //TODO implement the method
    }

    private class SelfWbAuthListener implements WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (token.isSessionValid()) {
                        // 保存 Token 到 SharedPreferences
                        AccessTokenKeeper.writeAccessToken(activity, token);
                    }
                }
            });
            if (token != null
                    && !TextUtils.isEmpty(token.getUid())
                    && !TextUtils.isEmpty(token.getToken())){
                requestUserData(token);
            }
        }

        @Override
        public void cancel() {
            Toast.makeText(activity,
                    R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            Toast.makeText(activity, errorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void requestUserData(Oauth2AccessToken token) {
        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("https://api.weibo.com/2/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient())
                .build();
        HashMap<String, String> map = new HashMap<>();
        map.put("access_token", token.getToken());
        map.put("uid",token.getUid());
        retrofit.create(UserApi.class)
                .getUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SinaUser>() {
                    @Override
                    public void call(SinaUser sinaUser) {
                        SinaLoginImpl.this.onSuccess(sinaUser);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        SinaLoginImpl.this.onFailure(throwable);
                    }
                });
    }


}
