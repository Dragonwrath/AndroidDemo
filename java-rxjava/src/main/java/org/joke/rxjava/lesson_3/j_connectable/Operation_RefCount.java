package org.joke.rxjava.lesson_3.j_connectable;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * RefCount
 * 让一个可连接的Observable行为像普通的Observable
 * 可连接的Observable (connectable Observable)与普通的Observable差不多，不过它并不会
 * 在被订阅时开始发射数据，而是直到使用了 Connect 操作符时才会开始。用这种方法，你可
 * 以在任何时候让一个Observable开始发射数据。
 * RefCount 操作符把从一个可连接的Observable连接和断开的过程自动化了。它操作一个可连
 * 接的Observable，返回一个普通的Observable。当第一个订阅者订阅这个Observable
 * 时， RefCount 连接到下层的可连接Observable。 RefCount 跟踪有多少个观察者订阅它，直
 * 到最后一个观察者完成才断开与下层可连接Observable的连接。
 * RxJava中的实现为 refCount ，还有一个操作符叫 share ，它的作用等价于对一个
 * Observable同时应用 publish 和 refCount 操作。
 * Javadoc: refCount())
 * Javadoc: share())
 */
public class Operation_RefCount {
    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_1();
        System.out.println();
    }

    private static void step_1() {
        Observable<Integer> observable = Observable.range(1, 1000000).sample(10, TimeUnit.MILLISECONDS).publish().refCount();
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer #1---> " + integer);
            }
        });
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer #2---> " + integer);
            }
        });
    }

    private static void step_2() {
        Observable<Integer> observable = Observable.range(1, 1000000).sample(10, TimeUnit.MILLISECONDS).share();
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer #1---> " + integer);
            }
        });
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer #2---> " + integer);
            }
        });
    }
}
