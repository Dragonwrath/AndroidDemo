package org.joke.rxjava.lesson_3.d_combine;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Zip
 * 通过一个函数将多个Observables的发射物结合到一起，基于这个函数的结果为每个结合体发
 * 射单个数据项。
 * Zip 操作符返回一个Obversable，它使用这个函数按顺序结合两个或多个Observables发射
 * 的数据项，然后它发射这个函数返回的结果。它按照严格的顺序应用这个函数。它只发射与
 * 发射数据项最少的那个Observable一样多的数据。
 * RxJava将这个操作符实现为 zip 和 zipWith 。
 * zip 的最后一个参数接受每个Observable发射的一项数据，返回被压缩后的数据，它可以接
 * 受一到九个参数：一个Observable序列，或者一些发射Observable的Observables。
 * Javadoc: zip(Iterable,FuncN))
 * Javadoc: zip(Observable,FuncN))
 * Javadoc: zip(Observable,Observable,Func2)) (最多可以有九个Observables参数)
 *
 *
 * zipWith 操作符总是接受两个参数，第一个参数是一个Observable或者一个Iterable。
 * Javadoc: zipWith(Observable,Func2))
 * Javadoc: zipWith(Iterable,Func2))
 *
 *
 * zip 和 zipWith 默认不在任何特定的操作符上执行。
 */
public class Operation_Zip {
    
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


    //zipWith将两个数据进行相应的压缩，一对一合并
    private static void step_1() {
        Observable.from(list).zipWith(Observable.from(list), new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

}
