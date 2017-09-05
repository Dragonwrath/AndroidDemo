package org.joke.rxjava.lesson_3.c_filter;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;


/**
 * Distinct
 * 抑制（过滤掉）重复的数据项
 * Distinct 的过滤规则是：只允许还没有发射过的数据项通过。
 * 在某些实现中，有一些变体允许你调整判定两个数据不同( distinct )的标准。还有一些实现
 * 只比较一项数据和它的直接前驱，因此只会从序列中过滤掉连续重复的数据。
 * Javadoc: distinct())


 * distinct(Func1)
 * 这个操作符有一个变体接受一个函数函数。这个函数根据原始Observable发射的数据项产生
 * 一个Key，然后，比较这些Key而不是数据本身，来判定两个数据是否是不同的。
 * Javadoc: distinct(Func1))


 * distinctUntilChanged
 * RxJava还是实现了一个 distinctUntilChanged 操作符。它只判定一个数据和它的直接前驱是
 * 否是不同的。
 * distinctUntilChanged(Func1)
 * 和 distinct(Func1) 一样，根据一个函数产生的Key判定两个相邻的数据项是不是不同的。
 * Javadoc: distinctUntilChanged(Func1))

 * distinct 和 distinctUntilChanged 默认不在任何特定的调度器上执行。
 */
public class Operation_Distinct {
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

    private static void step_1() {
        list.add(6);
        Observable.from(list).distinct().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    private static void step_2(){
        Observable.from(list).distinctUntilChanged(new Func2<Integer, Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer, Integer integer2) {
                return null;
            }
        });
    }

}
