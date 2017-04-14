package org.joke.rxjava.lesson_3.a_create;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;


/**
 * 直到有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable
 * Defer 操作符会一直等待直到有观察者订阅它，然后它使用Observable工厂方法生成一个Observable。
 * 它对每个观察者都这样做，因此尽管每个订阅者都以为自己订阅的是同一个Observable，
 * 事实上每个订阅者获取的是它们自己的单独的数据序列。
 * 在某些情况下，等待直到最后一分钟（就是知道订阅发生时）才生成Observable可以确保Observable包含最新的数据。
 *
 *RxJava将这个操作符实现为 defer 方法。这个操作符接受一个你选择的Observable工厂函数作为单个参数。
 * 这个函数没有参数，返回一个Observable。
 *
 *
 * defer 方法默认不在任何特定的调度器上执行。
 *
 */
public class Operation_Defer {
    public static void main(String[] args) {
        step1();
    }

    private static void step1() {
        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.unsafeCreate(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("1");
                    }
                });
            }
        });
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s = " + s);
            }
        });
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s = " + s);
            }
        });
    }
}
