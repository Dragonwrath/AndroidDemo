package org.joke.rxjava.lesson_3.a_create;

import rx.Observable;
import rx.Subscriber;


/**
 * 你可以使用 Create 操作符从头开始创建一个Observable，
 * 给这个操作符传递一个接受观察者作为参数的函数，
 * 编写这个函数让它的行为表现为一个Observable
 * --恰当的调用观察者的onNext，onError和onCompleted方法。
 * 一个形式正确的有限Observable必须尝试调用观察者的onCompleted正好一次或者它的
 * onError正好一次，而且此后不能再调用观察者的任何其它方法。
 * RxJava将这个操作符实现为 create 方法。
 * 建议你在传递给 create 方法的函数中检查观察者的 isUnsubscribed 状态，
 * 以便在没有观察者的时候，让你的Observable停止发射数据或者做昂贵的运算。
 *
 * create 方法默认不在任何特定的调度器上执行。
 */
public class Operation_Create {
    static int total;
    public static void main(String[] args) {

        step1();
    }

    private static void step2() {
        Observable.unsafeCreate(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int j = 0; j < 5; j++) {
                    subscriber.onNext(String.valueOf(j));
                    if (j == 4)
                        subscriber.onCompleted();
                }
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println("Next: " + s);
            }
        });
    }


    private static void step1() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int j = 0; j < 5; j++) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(String.valueOf(j));
                    } else
                        subscriber.onCompleted();
                }
            }
        });
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println("Next: " + s);
            }
        });
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println("Next: " + s);
            }
        });
    }
}
