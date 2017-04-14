package org.joke.rxjava.lesson_3.a_create;


import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * 创建一个按固定时间间隔发射整数序列的Observable
 * Interval 操作符返回一个Observable，它按固定的时间间隔发射一个无限递增的整数序列。
 * RxJava将这个操作符实现为 interval 方法。它接受一个表示时间间隔的参数和一个表示时间
 * 单位的参数。
 * Javadoc: interval(long,TimeUnit))
 * Javadoc: interval(long,TimeUnit,Scheduler))
 * 还
 * 还有一个版本的 interval 返回一个Observable，它在指定延迟之后先发射一个零值，然后再
 * 按照指定的时间间隔发射递增的数字。这个版本的 interval 在RxJava 1.0.0中叫做 timer ，
 * 但是那个方法已经不建议使用了，因为一个名叫 interval 的操作符有同样的功能。
 * Javadoc: interval(long,long,TimeUnit)) Javadoc: interval(long,long,TimeUnit,Scheduler))
 * interval 默认在 computation 调度器上执行。你也可以传递一个可选的Scheduler参数来指定
 * 调度器。
 */

/**
 * 底层调用了unsafeCreate方法，已经创建了相应的内容，输入的是自增长的long序列
 * 可以在执行soutv处增加相应的断点，之后，观察console输出，即可看到相应的内容
 * 此外默认的调度器是Schedulers.computation()
 */

public class Operation_Interval {
    public static void main(String[] args) {
        step_1();

        Schedulers.computation();
        System.out.println("args = " );
    }

    private static void step_1() {
        Observable<Long> observable = Observable.interval(2,2, TimeUnit.SECONDS).asObservable();
        observable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("aLong = " + aLong);
            }
        });
    }

}
