package org.joke.rxjava.lesson_3.d_combine;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * 在数据序列的开头插入一条指定的项
 * 如果你想要一个Observable在发射数据之前先发射一个指定的数据序列，可以使
 * 用 StartWith 操作符。（如果你想一个Observable发射的数据末尾追加一个数据序列可以使
 * 用 Concat 操作符。）
 * 可接受一个Iterable或者多个Observable作为函数的参数。
 * Javadoc: startWith(Iterable))
 * Javadoc: startWith(T)) (最多接受九个参数)
 * 你也可以传递一个Observable给 startWith ，它会将那个Observable的发射物插在原始
 * Observable发射的数据序列之前，然后把这个当做自己的发射物集合。这可以看作
 * 是 Concat 的反转。
 * Javadoc: startWith(Observable))
 */
public class Operation_StartWith {
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

    private static void step_1() {
        Observable.from(list).startWith(Observable.from(list)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }
}
