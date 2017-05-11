package com.yang.sinademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.yang.sinademo.login.LoginInterface;
import com.yang.sinademo.login.SinaLoginImpl;

public class LoginActivity extends AppCompatActivity {

    private LoginInterface loginImpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void sinaLogin(View view) {
        loginImpl =  new SinaLoginImpl(this);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(this);
        loginImpl.login();
//        if (!TextUtils.isEmpty(token.getRefreshToken()))
//            loginImpl.refresh();
//        else
//            loginImpl.login();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
       if (loginImpl != null) {
           loginImpl.callback(requestCode,resultCode,data);
       }
    }
}
