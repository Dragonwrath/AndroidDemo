package org.joke.rxjava.lesson_4.b_custom_transform;

import rx.Observable;
import rx.functions.Func1;

import java.lang.String;

public abstract class MyTransformer<Integer ,String > implements Observable.Transformer<Integer,String> {
    public MyTransformer() {
    }
    @Override
    public abstract Observable<String> call(Observable<Integer> source);
}