package org.joke.rxjava.lesson_3.f_observable_utility;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Timestamped;

/**
 * Timestamp
 * 给Observable发射的数据项附加一个时间戳
 * RxJava中的实现为 timestamp ，它将一个发射T类型数据的Observable转换为一个发射类型
 * 为 Timestamped<T> 的数据的Observable，每一项都包含数据的原始发射时间。
 * timestamp 默认在 immediate 调度器上执行，但是可以通过参数指定其它的调度器。
 * Javadoc: timestamp())
 * Javadoc: timestamp(Scheduler))
 */
public class Operation_Timestamp {
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
        Observable.from(list).timestamp().subscribe(new Action1<Timestamped<Integer>>() {
            @Override
            public void call(Timestamped<Integer> integerTimestamped) {
                Integer value = integerTimestamped.getValue();
                long l = System.nanoTime();
                System.out.println("value = "+ value + "      l = " + l);
            }
        });
    }
}
