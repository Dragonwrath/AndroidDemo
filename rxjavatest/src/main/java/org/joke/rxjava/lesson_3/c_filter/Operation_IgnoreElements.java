package org.joke.rxjava.lesson_3.c_filter;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;


/**
 * IgnoreElements
 * 不发射任何数据，只发射Observable的终止通知
 * IgnoreElements 操作符抑制原始Observable发射的所有数据，只允许它的终止通知
 * （ onError 或 onCompleted ）通过。
 * 如果你不关心一个Observable发射的数据，但是希望在它完成时或遇到错误终止时收到通
 * 知，你可以对Observable使用 ignoreElements 操作符，它会确保永远不会调用观察者
 * 的 onNext() 方法。
 * RxJava将这个操作符实现为 ignoreElements 。
 * Javadoc: ignoreElements())
 * ignoreElements 默认不在任何特定的调度器上执行。
 */
public class Operation_IgnoreElements {
    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_1();
    }

    private static void step_1(){
        Observable.from(list).ignoreElements().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

}
