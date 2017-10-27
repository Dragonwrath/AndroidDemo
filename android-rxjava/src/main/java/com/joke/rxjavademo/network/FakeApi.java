package com.joke.rxjavademo.network;

import android.support.annotation.NonNull;

import com.joke.rxjavademo.model.FakeThing;
import com.joke.rxjavademo.model.FakeToken;

import java.util.Random;

import rx.Observable;
import rx.functions.Func1;

public class FakeApi {
    private static FakeApi api ;
    private final Random random = new Random();

    public static FakeApi newInstance(){
        if (api == null)
            synchronized (FakeApi.class) {
                api = new FakeApi();
            }
        return api;
    }


    public  Observable<FakeToken> getFakeToken(@NonNull String fakeAuth) {
        return Observable.just(fakeAuth)
                .map(new Func1<String, FakeToken>() {
                    @Override
                    public FakeToken call(String s) {
                        int fakeNetworkTimeCost = random.nextInt(500) + 500;
                        try {
                            Thread.sleep(fakeNetworkTimeCost);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        FakeToken fakeToken = new FakeToken();
                        fakeToken.token = createToken();
                        return fakeToken;
                    }
                });
    }

    private String createToken() {
        return "fake_token_" + System.nanoTime()%1000;
    }

    public Observable<FakeThing> getFakeData(FakeToken fakeToken) {
        return Observable.just(fakeToken)
                .map(new Func1<FakeToken, FakeThing>() {
                    @Override
                    public FakeThing call(FakeToken fakeToken) {
                        FakeThing fakeThing = new FakeThing();
                        fakeThing.id = (int) (System.nanoTime() % 1000);
                        fakeThing.name = "FAKE_USER_" + fakeThing.id;
                        return fakeThing;
                    }
                });
    }
}
