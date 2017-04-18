package org.joke.rxjava.lesson_3.j_connectable;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;


/**
 一个可连接的Observable与普通的Observable差不多，除了这一点：可连接的Observable在
 被订阅时并不开始发射数据，只有在它的 connect() 被调用时才开始。用这种方法，你可以
 等所有的潜在订阅者都订阅了这个Observable之后才开始发射数据。
 下面的示例代码展示了两个订阅者订阅同一个Observable的情况。第
 一种情形，它们订阅一个普通的Observable；第二种情形，它们订阅一个可连接的
 Observable，并且在两个都订阅后再连接。
 */

/**
 * Connect
 * 让一个可连接的Observable开始发射数据给订阅者
 * 可连接的Observable (connectable Observable)与普通的Observable差不多，不过它并不会
 * 在被订阅时开始发射数据，而是直到使用了 Connect 操作符时才会开始。用这个方法，你可
 * 以等待所有的观察者都订阅了Observable之后再开始发射数据。
 * RxJava中 connect 是 ConnectableObservable 接口的一个方法，使用 publish 操作符可以将一
 * 个普通的Observable转换为一个 ConnectableObservable 。
 * 调用 ConnectableObservable 的 connect 方法会让它后面的Observable开始给发射数据给订阅
 * 者。
 * connect 方法返回一个 Subscription 对象，可以调用它的 unsubscribe 方法让Observable停
 * 止发射数据给观察者。
 * 即使没有任何订阅者订阅它，你也可以使用 connect 方法让一个Observable开始发射数据
 * （或者开始生成待发射的数据）。这样，你可以将一个"冷"的Observable变为"热"的。
 * Javadoc: connect())
 * Javadoc: connect(Action1))
 */

public class Operation_Connect {

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

    //使用ConnectableObservable的时候，只有在connect之后在发送数据
    //使用publish 可以将普通的Observable 转换为ConnectableObservable
    private static void step_1() {
        ConnectableObservable<Integer> observable = Observable.range(1, 1000000).sample(10, TimeUnit.MILLISECONDS).publish();
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
        observable.connect();
    }

    //普通的Observable，发射数据是第一个订阅者发射的数据完成之后
    //第二个订阅者发射的数据
    private static void step_2() {
        Observable<Integer> observable = Observable.range(1, 1000000).sample(10, TimeUnit.MILLISECONDS);
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
