package org.joke.rxjava.lesson_3.c_filter;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Skip
 * 抑制Observable发射的前N项数据
 * 使用 Skip 操作符，你可以忽略Observable'发射的前N项数据，只保留之后的数据。
 * RxJava中这个操作符叫 skip 。 skip 的这个变体默认不在任何特定的调度器上执行。
 * Javadoc: skip(int))
 *
 *
 * skip 的这个变体接受一个时长而不是数量参数。它会丢弃原始Observable开始的那段时间发
 射的数据，时长和时间单位通过参数指定。
 * skip 的这个变体默认在 computation 调度器上执行，但是你可以使用第三个参数指定其它的
 * 调度器。
 * Javadoc: skip(long,TimeUnit))
 * Javadoc: skip(long,TimeUnit,Scheduler))
 */
public class Operation_Skip {
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

    private static void step_1() {
        Observable.from(list).skip(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }
}
