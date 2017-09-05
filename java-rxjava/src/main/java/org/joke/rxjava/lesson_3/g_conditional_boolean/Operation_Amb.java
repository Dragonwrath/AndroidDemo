package org.joke.rxjava.lesson_3.g_conditional_boolean;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/** Amb
 * 给定两个或多个Observables，它只发射首先发射数据或通知的那个Observable的所有数据
 * 当你传递多个Observable给 Amb 时，它只发射其中一个Observable的数据和通知：首先发送
 * 通知给 Amb 的那个，不管发射的是一项数据还是一个 onError 或 onCompleted 通知。 Amb 将
 * 忽略和丢弃其它所有Observables的发射物。
 * RxJava的实现是 amb ，有一个类似的对象方法 ambWith 。例
 * 如， Observable.amb(o1,o2) 和 o1.ambWith(o2) 是等价的。
 * 这个操作符默认不在任何特定的调度器上执行。
 */
 public class Operation_Amb {
    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 9; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_1();
    }

    //ambWith
    //可以传递给ambWith两个observable，它会首先响应第一个发送数据的Observable，
    //用来继续发送相应的数据
    private static void step_1() {
        Observable.from(list).ambWith(Observable.just(101,102))
            .subscribe(new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    System.out.println("integer = " + integer);
                }
            });
    }

}
