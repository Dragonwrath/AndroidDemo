package org.joke.rxjava.lesson_3.f_observable_utility;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Serialize 
 * 强制一个Observable连续调用并保证行为正确 
 * 一个Observable可以异步调用它的观察者的方法，可能是从不同的线程调用。这可能会让 
 * Observable行为不正确，它可能会在某一个 onNext 调用之前尝试调 
 * 用 onCompleted 或 onError 方法，或者从两个不同的线程同时调用 onNext 方法。使 
 * 用 Serialize 操作符，你可以纠正这个Observable的行为，保证它的行为是正确的且是同步 
 * 的。 
 * RxJava中的实现是 serialize ，它默认不在任何特定的调度器上执行。 
 * Javadoc: serialize()) 
 */
public class Operation_Serialize {
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
        Observable.from(list).serialize().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

}
