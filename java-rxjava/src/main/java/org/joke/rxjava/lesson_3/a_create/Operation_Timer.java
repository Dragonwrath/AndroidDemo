package org.joke.rxjava.lesson_3.a_create;


import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * 创建一个Observable，它在一个给定的延迟后发射一个特殊的值。
 * Timer 操作符创建一个在给定的时间段之后返回一个特殊值的Observable。
 * RxJava将这个操作符实现为 timer 函数。
 * timer 返回一个Observable，它在延迟一段给定的时间后发射一个简单的数字0。
 * timer 操作符默认在 computation 调度器上执行。有一个变体可以通过可选参数指定
 * Scheduler。
 * Javadoc: timer(long,TimeUnit))
 * Javadoc: timer(long,TimeUnit,Scheduler))
 */

/**
 * 这个操作符与interval的区别是
 * 1、interval 实在指定的间隔时间内发送数据，不停的循环，从0L开始
 * 2、timer 是在指定的时间后发送数据数据0L
 */
public class Operation_Timer {
    public static void main(String[] args) {
        step_1();
        System.out.println("args = " + args);
    }

    private static void step_1 () {
        Observable.timer(10, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("aLong = " + aLong);
                    }
                });
    }

}
