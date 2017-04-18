package org.joke.rxjava.lesson_4.a_custom_operator;


import rx.Completable.Operator;
import rx.Observable;
import rx.Single;
import rx.Subscriber;


public class MyOperator<T,R> implements Observable.Operator<T,R> {

    public MyOperator() {
    }

    @Override
    public Subscriber<? super R> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<R>(subscriber) {
            @Override
            public void onCompleted() {
            /* 这里添加你自己的onCompleted行为，或者仅仅传递完成通知： */
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }

            @Override
            public void onError(Throwable e) {
            /* 这里添加你自己的onError行为, 或者仅仅传递错误通知：*/
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }

            @Override
            public void onNext(R r) {
                /* 这个例子对结果的每一项执行排序操作，然后返回这个结果 */
                if (!subscriber.isUnsubscribed()) {

                }
            }
        };
    }
}