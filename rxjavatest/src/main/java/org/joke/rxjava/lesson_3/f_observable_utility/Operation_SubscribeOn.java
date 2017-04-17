package org.joke.rxjava.lesson_3.f_observable_utility;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;


/**
 * SubscribeOn
 * 指定Observable自身在哪个调度器上执行
 * 很多ReactiveX实现都使用调度器 " Scheduler "来管理多线程环境中Observable的转场。你可
 * 以使用 SubscribeOn 操作符指定Observable在一个特定的调度器上运转。
 * ObserveOn 操作符的作用类似，但是功能很有限，它指示Observable在一个指定的调度器上
 * 给观察者发通知。
 * 在某些实现中还有一个 UnsubscribeOn 操作符。
 * Javadoc: subscribeOn(Scheduler))
 * Javadoc: unsubscribeOn(Scheduler))
 */
public class Operation_SubscribeOn {
    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 9; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_1();

        System.out.println();
    }

    private static void step_1() {
        Observable.from(list).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

            }
        });
    }
}
