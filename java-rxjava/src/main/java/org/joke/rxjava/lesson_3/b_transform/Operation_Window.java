package org.joke.rxjava.lesson_3.b_transform;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Emitter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.Subject;

/**
 * Window
 * 定期将来自原始Observable的数据分解为一个Observable窗口，发射这些窗口，而不是每次
 * 发射一项数据Window 和 Buffer 类似，但不是发射来自原始Observable的数据包，它发射的是
 * Observables，这些Observables中的每一个都发射原始Observable数据的一个子集，最后发
 * 射一个 onCompleted 通知。
 * 和 Buffer 一样， Window 有很多变体，每一种都以自己的方式将原始Observable分解为多个
 * 作为结果的Observable，每一个都包含一个映射原始数据的 window 。用 Window 操作符的术
 * 语描述就是，当一个窗口打开(when a window "opens")意味着一个新的Observable已经发射
 * （产生）了，而且这个Observable开始发射来自原始Observable的数据；当一个窗口关闭
 * (when a window "closes")意味着发射(产生)的Observable停止发射原始Observable的数据，
 * 并且发射终止通知 onCompleted 给它的观察者们。
 * 在RxJava中有许多种 Window 操作符的变体。
 *
 * window(closingSelector)
 * window 的这个变体会立即打开它的第一个窗口。每当它观察到 closingSelector 返回的
 * Observable发射了一个对象时，它就关闭当前打开的窗口并立即打开一个新窗口。用这个方
 * 法，这种 window 变体发射一系列不重叠的窗口，这些窗口的数据集合与原始Observable发射
 * 的数据是一一对应的。
 * Javadoc: window(Func0))
 *
 *
 * window(windowOpenings, closingSelector)
 * 无论何时，只要 window 观察到 windowOpenings 这个Observable发射了一个 Opening 对象，它
 * 就打开一个窗口，并且同时调用 closingSelector 生成一个与那个窗口关联的关闭
 * (closing)Observable。当这个关闭(closing)Observable发射了一个对象时， window 操作符就
 * 会关闭那个窗口。对这个变体来说，由于当前窗口的关闭和新窗口的打开是由单独的
 * Observable管理的，它创建的窗口可能会存在重叠（重复某些来自原始Observable的数据）
 * 或间隙（丢弃某些来自原始Observable的数据）。
 * Javadoc: window(Observable,Func1))
 *
 *
 * window(count)
 * 这个 window 的变体立即打开它的第一个窗口。每当当前窗口发射了 count 项数据，它就关闭
 * 当前窗口并打开一个新窗口。如果从原始Observable收到了 onError 或 onCompleted 通知它
 * 也会关闭当前窗口。这种 window 变体发射一系列不重叠的窗口，这些窗口的数据集合与原始
 * Observable发射的数据是一一对应的。
 * Javadoc: window(int))
 *
 *
 * window(count, skip)
 * 这个 window 的变体立即打开它的第一个窗口。原始Observable每发射 skip 项数据它就打开
 * 一个新窗口（例如，如果 skip 等于3，每到第三项数据，它会打开一耳光新窗口）。每当当
 * 前窗口发射了 count 项数据，它就关闭当前窗口并打开一个新窗口。如果从原始Observable
 * 收到了 onError 或 onCompleted 通知它也会关闭当前窗口。如果 skip=count ，它的行为
 * 与 window(source, count) 相同；如果 skip < count ，窗口可会有 count - skip 个重叠的数
 * 据；如果 skip > count ，在两个窗口之间会有 skip - count 项数据被丢弃。
 * Javadoc: window(int,int))
 *
 *
 * window(timespan, unit[, scheduler])
 * 这个 window 的变体立即打开它的第一个窗口。每当过了 timespan 这么长的时间它就关闭当
 * 前窗口并打开一个新窗口（时间单位是 unit ，可选在调度器 scheduler 上执行）。如果从原
 * 始Observable收到了 onError 或 onCompleted 通知它也会关闭当前窗口。这种 window 变体发
 * 射一系列不重叠的窗口，这些窗口的数据集合与原始Observable发射的数据也是一一对应
 * 的。
 * Javadoc: window(long,TimeUnit))
 * Javadoc: window(long,TimeUnit,Scheduler))
 *
 *
 * window(timespan, unit, count[, scheduler])
 * 这个 window 的变体立即打开它的第一个窗口。这个变体
 * 是 window(count) 和 window(timespan, unit[, scheduler]) 的结合，每当过了 timespan 的时长
 * 或者当前窗口收到了 count 项数据，它就关闭当前窗口并打开另一个。如果从原始
 * Observable收到了 onError 或 onCompleted 通知它也会关闭当前窗口。这种 window 变体发射
 * 一系列不重叠的窗口，这些窗口的数据集合与原始Observable发射的数据也是一一对应的。
 * Javadoc: window(long,TimeUnit,int))
 * Javadoc: window(long,TimeUnit,int,Scheduler))
 *
 *
 * window(timespan, timeshift, unit[, scheduler])
 * buffer(timespan, timeshift, unit) 在每一个 timeshift 时期内都创建一个新的 List ,然后用
 * 原始Observable发射的每一项数据填充这个列表（在把这个 List 当做自己的数据发射前，从
 * 创建时开始，直到过了 timespan 这么长的时间）。如果 timespan 长于 timeshift ，它发射的
 * 数据包将会重叠，因此可能包含重复的数据项。
 * 这个 window 的变体立即打开它的第一个窗口。随后每当过了 timeshift 的时长就打开一个新
 * 窗口（时间单位是 unit ，可选在调度器 scheduler 上执行），当窗口打开的时长达
 * 到 timespan ，它就关闭当前打开的窗口。如果从原始Observable收到
 * 了 onError 或 onCompleted 通知它也会关闭当前窗口。窗口的数据可能重叠也可能有间隙，取
 * 决于你设置的 timeshift 和 timespan 的值。
 * 这个变体的 window 默认在 computation 调度器上执行它的定时器。
 * Javadoc: window(long,long,TimeUnit))
 * Javadoc: window(long,long,TimeUnit,Scheduler))
 *
 *
 * window-backpressure
 * 你可以使用 Window 操作符实现反压 backpressure （意思是，处理这样一个Observable：它
 * 产生数据的数据可能比它的观察者消费数据的数据快）。
 * Window操作符可以将大量的数据序列缩减为较少的数据窗口序列，让它们更容易处理。例
 * 如，你可以按固定的时间间隔，定期关闭和发射来自一个爆发性Observable的数据窗口。
 * 示例代码
 * Observable<Observable<Integer>> burstyWindowed =
 * bursty.window(500, TimeUnit.MILLISECONDS);
 * 你还可以选择每当收到爆发性Observable的N项数据时发射一个新的数据窗口。
 * 示例代码
 * Observable<Observable<Integer>> burstyWindowed = bursty.window(5);
 */
public class Operation_Window {
    public static void main(String[] args) {
        init();
        step_3();
        System.out.println();
    }


    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    //window(windowOpenings, closingSelector)
    private static void step_1() {
        Observable.from(list).delay(10, TimeUnit.SECONDS)
                .window(Observable.interval(2, TimeUnit.SECONDS), new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long aLong) {
                        return Observable.interval(3, TimeUnit.SECONDS);
                    }
                }).subscribe(new Action1<Observable<Integer>>() {
            @Override
            public void call(Observable<Integer> integerObservable) {
                integerObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = " + integer);
                    }
                });
            }
        });
    }

    //window(count)
    private static void step_2() {
        Observable.from(list).window(3)
                .subscribeOn(Schedulers.computation())
                .subscribe(new Action1<Observable<Integer>>() {
                    @Override
                    public void call(Observable<Integer> integerObservable) {
                        integerObservable.subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                System.out.println("integer = " + integer);
                            }
                        });
                    }
                });
    }

    //window(count, skip)
    //skip的数字如果于window的数字相等，那么将不会跳过任何一个数据
    private static void step_3() {
        Observable.from(list).window(3,2)
                .subscribe(new Action1<Observable<Integer>>() {
                    @Override
                    public void call(Observable<Integer> integerObservable) {
                        integerObservable.subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                System.out.println("integer = " + integer);
                            }
                        });
                    }
                });
    }

    private static void step_4() {

    }
    private static void step_5() {

    }

    private static void step_6() {

    }

}
