package com.yang.sinademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.yang.sinademo.login.SinaError;
import com.yang.sinademo.login.SinaUser;

import java.util.HashMap;


import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private final static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private final static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    private TextView text,text1,text2,text3,text4;
    private ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        text = (TextView)findViewById(R.id.text);
        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);
        image1 = (ImageView) findViewById(R.id.image1);
    }

    //微博授权
    public void onOauth(View view) {
        startActivityForResult(new Intent(this, WBAuthActivity.class),101);
    }

    public void show(Oauth2AccessToken token){
        String builder = (token.getUid() + "\r\n") +
                token.getToken() + "\r\n" +
                token.getExpiresTime() + "\r\n" +
                token.getPhoneNum() + "\r\n" +
                token.getRefreshToken();
        text.setText(builder);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 101 && resultCode == RESULT_OK) {
            show(AccessTokenKeeper.readAccessToken(this));
        }
    }

    public void onGetUser(View view) {
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(this);
        HashMap<String, String> map = new HashMap<>();
        map.put("access_token", token.getToken());
        map.put("uid",token.getUid());
        buildRetrofit().create(UserApi.class).getUser(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private Observer<SinaUser> observer = new Observer<SinaUser>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            //如果是RESTful类型，那么在HttpException可以直接获取到相应的错误内容
            //以及错误code进行相应的区分
            //如果不是RESTful，那么可以参考的方式是，将其利用Adapter进行转换
            //关于RESTfull，就是在利用相应的返回码进行相应的判断，
            //如果是200，那么走正常的业务逻辑，
            //如果是其他的值，会进行相应的判断，之后调用Retrofit.error()方法，
            //传递出相应的错误内容
            if (e instanceof HttpException) {
                HttpException exception = (HttpException) e;
                final retrofit2.Response<?> response = exception.response();
                Observable.just(exception)
                        .map(new Func1<HttpException, SinaError>() {
                            @Override
                            public SinaError call(HttpException e) {
                                ResponseBody responseBody = response.errorBody();
                                String s = null;
                                try {
                                    byte[] bytes;
                                     bytes = responseBody.bytes();
                                     s = new String(bytes);

                                } catch (Exception ex){
                                }
                                if (s != null)
                                    Log.e(TAG, "call: "+s );
                                return new Gson().fromJson(s,SinaError.class);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<SinaError>() {
                            @Override
                            public void call(SinaError error) {
                                if (error != null)
                                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        });
            }
        }

        @Override
        public void onNext(SinaUser user) {
            text1.setText(user.getName());
            text2.setText(user.getCity());
            text3.setText(user.getIdstr());
            text4.setText(user.getDescription());
            Glide.with(MainActivity.this).load(user.getProfile_image_url()).into(image1);
        }
    };

    public Retrofit retrofit;
    public Retrofit buildRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.weibo.com/2/users/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .client(okHttpClient)
                    .build()
                    ;
        }
        return retrofit;
    }


}
