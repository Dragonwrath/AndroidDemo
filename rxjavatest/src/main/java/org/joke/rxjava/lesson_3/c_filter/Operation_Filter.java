package org.joke.rxjava.lesson_3.c_filter;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Filter
 * 只发射通过了谓词测试的数据项
 * Filter操作符使用你指定的一个谓词函数测试数据项，只有通过测试的数据才会被发射。
 * RxJava将这个操作符实现为 filter 函数。
 */

/**
 * ofType
 * ofType 是 filter 操作符的一个特殊形式。它过滤一个Observable只返回指定类型的数据。
 * ofType 默认不在任何特定的调度器上指定。
 * Javadoc: ofType(Class))
 */
public class Operation_Filter {

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
        Observable.from(list).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return null;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }



    private static void step_2(){
        final ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add("haha");
        objects.add(3l);
        Observable.from(objects).ofType(Integer.class).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                int i = objects.indexOf(integer);
                System.out.println("integer = " + i);
            }
        });
    }
}

