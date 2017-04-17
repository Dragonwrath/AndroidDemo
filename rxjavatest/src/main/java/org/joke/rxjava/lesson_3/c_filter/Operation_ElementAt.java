package org.joke.rxjava.lesson_3.c_filter;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;


/**
 * ElementAt
 * 只发射第N项数据
 * ElementAt 操作符获取原始Observable发射的数据序列指定索引位置的数据项，然后当做自
 * 己的唯一数据发射。
 * RxJava将这个操作符实现为 elementAt ，给它传递一个基于0的索引值，它会发射原始
 * Observable数据序列对应索引位置的值，如果你传递给 elementAt 的值为5，那么它会发射第
 * 六项的数据。
 * 如果你传递的是一个负数，或者原始Observable的数据项数小于 index+1 ，将会抛出一
 * 个 IndexOutOfBoundsException 异常。
 * Javadoc: elementAt(int))

 * elementAtOrDefault
 * RxJava还实现了 elementAtOrDefault 操作符。与 elementAt 的区别是，如果索引值大于数据
 * 项数，它会发射一个默认值（通过额外的参数指定），而不是抛出异常。但是如果你传递一
 * 个负数索引值，它仍然会抛出一个 IndexOutOfBoundsException 异常。
 * Javadoc: elementAtOrDefault(int,T))
 * elementAt 和 elementAtOrDefault 默认不在任何特定的调度器上执行。
 */
public class Operation_ElementAt {
    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_1();

    }

    private static void step_1(){
        Observable.from(list).elementAt(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }
}
