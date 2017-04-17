package org.joke.rxjava.lesson_3.f_observable_utility;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.TimeInterval;


/**
 * Timeout
 * 对原始Observable的一个镜像，如果过了一个指定的时长仍没有发射数据，它会发一个错误
 * 通知
 * 如果原始Observable过了指定的一段时长没有发射任何数据， Timeout 操作符会以一
 * 个 onError 通知终止这个Observable。
 * RxJava中的实现为 timeout ，但是有好几个变体。
 * 第一个变体接受一个时长参数，每当原始Observable发射了一项数据， timeout 就启动一个
 * 计时器，如果计时器超过了指定指定的时长而原始Observable没有发射另一项数
 * 据， timeout 就抛出 TimeoutException ，以一个错误通知终止Observable。
 * 这个 timeout 默认在 computation 调度器上执行，你可以通过参数指定其它的调度器。
 * Javadoc: timeout(long,TimeUnit))
 * Javadoc: timeout())
 *
 *
 * 这个版本的 timeout 在超时时会切换到使用一个你指定的备用的Observable，而不是发错误
 * 通知。它也默认在 computation 调度器上执行。
 * Javadoc: timeout(long,TimeUnit,Observable))
 * Javadoc: timeout(long,TimeUnit,Observable,Scheduler))
 *
 *
 * 这个版本的 timeout 使用一个函数针对原始Observable的每一项返回一个Observable，如果
 * 当这个Observable终止时原始Observable还没有发射另一项数据，就会认为是超时
 * 了， timeout 就抛出 TimeoutException ，以一个错误通知终止Observable。
 * 这个 timeout 默认在 immediate 调度器上执行。
 * Javadoc: timeout(Func1))
 *
 *
 * 这个版本的 timeout 同时指定超时时长和备用的Observable。它默认在 immediate 调度器上
 * 执行。
 * Javadoc: timeout(Func1,Observable))
 * 这个版本的 time 除了给每一项设置超时，还可以单独给第一项设置一个超时。它默认
 * 在 immediate 调度器上执行。
 * Javadoc: timeout(Func0,Func1))
 *
 *
 * 同上，但是同时可以指定一个备用的Observable。它默认在 immediate 调度器上执行。
 * Javadoc: timeout(Func0,Func1,Observable))
 */
public class Operation_Timeout {
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
        Observable.from(list);
    }
}
