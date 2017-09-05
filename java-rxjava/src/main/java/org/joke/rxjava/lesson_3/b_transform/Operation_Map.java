package org.joke.rxjava.lesson_3.b_transform;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 对Observable发射的每一项数据应用一个函数，执行变换操作
 * Map 操作符对原始Observable发射的每一项数据应用一个你选择的函数，然后返回一个发射
 * 这些结果的Observable。
 * RxJava将这个操作符实现为 map 函数。这个操作符默认不在任何特定的调度器上执行。
 * Javadoc: map(Func1))
 */

/**
 * cast
 * cast 操作符将原始Observable发射的每一项数据都强制转换为一个指定的类型，然后再发射
 * 数据，它是 map 的一个特殊版本。
 * Javadoc: cast(Class))
 */


//---------------------分隔线---------------------------------------------
//以下的内容属于可选包：StringObservable

/**
 * encode
 * encode 在 StringObservable 类中，不是标准RxJava的一部分，它也是一个特殊的 map 操作
 * 符。 encode 将一个发射字符串的Observable变换为一个发射字节数组（这个字节数组按照原
 * 始字符串中的多字节字符边界划分）的Observable。
 */

 /**
 * byLine
 * byLine 同样在 StringObservable 类中，也不是标准RxJava的一部分，它也是一个特殊
 * 的 map 操作符。 byLine 将一个发射字符串的Observable变换为一个按行发射来自原始
 * Observable的字符串的Observable。
 */



public class Operation_Map {

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        init();
        step_2();

    }


    private static void init() {
        for (int i = 0; i < 9; i++) {
            list.add(i);
        }
    }


    private static void step_1() {
        Observable.from(list).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer + 100;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    private static void step_2() {
        Observable.from(list).cast(Long.class)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long s) {
                        System.out.println(s);
                    }
                });
    }
}
