package org.joke.rxjava.lesson_3.c_filter;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;


/**
 * Take
 * 只发射前面的N项数据
 * 使用 Take操作符让你可以修改Observable的行为，只返回前面的N项数据，然后发射完成通
 * 知，忽略剩余的数据。
 * RxJava将这个操作符实现为 take函数。
 * 如果你对一个Observable使用 take(n) （或它的同义词 limit(n)）操作符，而那个
 * Observable发射的数据少于N项，那么 take操作生成的Observable不会抛异常或发
 * 射 onError通知，在完成前它只会发射相同的少量数据。
 * take(int)默认不任何特定的调度器上执行。
 * Javadoc:take(int))
 
 */
public class Operation_Take {
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
        Observable.from(list).take(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }
}
