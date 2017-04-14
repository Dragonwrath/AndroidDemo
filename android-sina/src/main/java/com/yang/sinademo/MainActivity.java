package com.yang.sinademo;

import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sina.weibo.sdk.WeiboAppManager;

public class MainActivity extends AppCompatActivity {

    private static SpecialHandler<MainActivity> handler ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new SpecialHandler<>(MainActivity.this);
    }

    //微博授权
    public void onOauth(View view) {
        Message message = handler.obtainMessage();
        message.what = 1;
        message.obj = new String(new byte[]{});

        handler.sendMessage(message);

        startActivity(new Intent(this, WBAuthActivity.class));

    }
}
