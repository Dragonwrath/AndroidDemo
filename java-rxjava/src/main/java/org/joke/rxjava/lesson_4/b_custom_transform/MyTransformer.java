package org.joke.rxjava.lesson_4.b_custom_transform;

import rx.Observable;

public abstract class MyTransformer<Integer ,String > implements Observable.Transformer<Integer,String> {
    public MyTransformer() {
    }
    @Override
    public abstract Observable<String> call(Observable<Integer> source);
}