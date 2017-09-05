package org.joke.rxjava.lesson_3.f_observable_utility;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;


/**
 * Delay
 * 延迟一段指定的时间再发射来自Observable的发射物
 * Delay 操作符让原始Observable在发射每项数据之前都暂停一段指定的时间段。效果是
 * Observable发射的数据项在时间上向前整体平移了一个增量。
 * RxJava的实现是 delay 和 delaySubscription 。
 * 第一种 delay 接受一个定义时长的参数（包括数量和单位）。每当原始Observable发射一项
 * 数据， delay 就启动一个定时器，当定时器过了给定的时间段时， delay 返回的Observable
 * 发射相同的数据项。
 * 注意： delay 不会平移 onError 通知，它会立即将这个通知传递给订阅者，同时丢弃任何待
 * 发射的 onNext 通知。然而它会平移一个 onCompleted 通知。
 * delay 默认在 computation 调度器上执行，你可以通过参数指定使用其它的调度器。
 * Javadoc: delay(long,TimeUnit))
 * Javadoc: delay())
 *
 *
 * 另一种 delay 不实用常数延时参数，它使用一个函数针对原始Observable的每一项数据返回
 * 一个Observable，它监视返回的这个Observable，当任何那样的Observable终止
 * 时， delay 返回的Observable就发射关联的那项数据。
 * 这种 delay 默认不在任何特定的调度器上执行。
 * Javadoc: delay(Func1))
 *
 *
 * 这个版本的 delay 对每一项数据使用一个Observable作为原始Observable的延时定时器。
 * 这种 delay 默认不在任何特定的调度器上执行。
 * Javadoc: delay(Func0,Func1))
 *
 *
 * 还有一个操作符 delaySubscription 让你你可以延迟订阅原始Observable。它结合搜一个定义
 * 延时的参数。
 * delaySubscription 默认在 computation 调度器上执行，你可以通过参数指定使用其它的调度
 * 器。
 * Javadoc: delaySubscription(long,TimeUnit))
 * Javadoc: delaySubscription(long,TimeUnit,Scheduler))
 *
 *
 * 还有一个版本的 delaySubscription 使用一个Obseable而不是一个固定的时长来设置订阅延
 * 时。
 * 这种 delaySubscription 默认不在任何特定的调度器上执行。
 * Javadoc: delaySubscription(Func0))
 */
public class Operartion_Delay {
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
        Observable.from(list).delay(3, TimeUnit.SECONDS).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }
}
