package org.joke.rxjava.lesson_3.d_combine;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;


/**
 * Join
 * 任何时候，只要在另一个Observable发射的数据定义的时间窗口内，这个Observable发射了
 * 一条数据，就结合两个Observable发射的数据。
 * Join 操作符结合两个Observable发射的数据，基于时间窗口（你定义的针对每条数据特定的
 * 原则）选择待集合的数据项。你将这些时间窗口实现为一些Observables，它们的生命周期从
 * 任何一条Observable发射的每一条数据开始。当这个定义时间窗口的Observable发射了一条
 * 数据或者完成时，与这条数据关联的窗口也会关闭。只要这条数据的窗口是打开的，它将继
 * 续结合其它Observable发射的任何数据项。你定义一个用于结合数据的函数。
 * 很多ReactiveX实现还有一个类似的 GroupJoin 操作符。
 * Most ReactiveX implementations that have a Join operator also have a GroupJoin operator
 * that is similar, except that the function you define to combine items emitted by the two
 * Observables pairs individual items emitted by the source Observable not with an item from
 * the second Observable, but with an Observable that emits items from the second
 * Observable that fall in the same window.

 * The join operator takes four parameters:
 * 1. the second Observable to combine with the source Observable
 * 2. a function that accepts an item from the source Observable and returns an Observable
 *      whose lifespan governs the duration during which that item will combine with items from
 *      the second Observable
 * 3. a function that accepts an item from the second Observable and returns an Observable
 *      whose lifespan governs the duration during which that item will combine with items from
 *      the first Observable
 * 4. a function that accepts an item from the first Observable and an item from the second
 *      Observable and returns an item to be emitted by the Observable returned from join
 * join 默认不在任何特定的调度器上执行。
 * Javadoc: Join(Observable,Func1,Func1,Func2))

 * The groupJoin operator takes four parameters:
 * 1. the second Observable to combine with the source Observable
 * 2. a function that accepts an item from the source Observable and returns an Observable
 *      whose lifespan governs the duration during which that item will combine with items from
 *      the second Observable
 * 3. a function that accepts an item from the second Observable and returns an Observable
 *      whose lifespan governs the duration during which that item will combine with items from
 *      the first Observable
 * 4. a function that accepts an item from the first Observable and an Observable that emits
 *      items from the second Observable and returns an item to be emitted by the Observable
 *      returned from groupJoin
 * groupJoin 默认不在任何特定的调度器上执行。
 * Javadoc: groupJoin(Observable,Func1,Func1,Func2))

 * 可选的 StringObservable 类中也有一个 join 操作符。它将一个发射字符串序列的Observable
 * 转换为一个发射单个字符串的Observable， join 操作符使用指定的定界符将全部单独的字符
 * 串连接起来。
 */
public class Operation_Join {
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


    //
    private static void step_1() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("-------String "+ i +"---->");
        }
        Observable.from(list).join(Observable.from(strings), new Func1<Integer, Observable<Long>>() {
            @Override
            public Observable<Long> call(Integer integer) {
                return Observable.interval(1, TimeUnit.MILLISECONDS);
            }
        }, new Func1<String, Observable<Long>>() {
            @Override
            public Observable<Long> call(String integer) {
                return Observable.interval(1, TimeUnit.MILLISECONDS);
            }
        }, new Func2<Integer, String, String>() {
            @Override
            public String call(Integer integer, String integer2) {
                return integer2 + integer;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    //具体区别看
    private static void step_2() {
        Observable.from(list).groupJoin(Observable.from(list), new Func1<Integer, Observable<Long>>() {
            @Override
            public Observable<Long> call(Integer integer) {
                return Observable.interval(1, TimeUnit.MICROSECONDS);
            }
        }, new Func1<Integer, Observable<Long>>() {
            @Override
            public Observable<Long> call(Integer integer) {
                return Observable.interval(1, TimeUnit.MICROSECONDS);
            }
        }, new Func2<Integer, Observable<Integer>, Integer>() {
            @Override
            public Integer call(final Integer integer, Observable<Integer> integerObservable) {
                integerObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer i) {
                        System.out.println("right integer = " + (i+ integer));
                    }
                });
                return null;
            }
        }).subscribe();
    }
}
