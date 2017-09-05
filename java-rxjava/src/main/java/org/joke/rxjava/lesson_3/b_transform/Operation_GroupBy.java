package org.joke.rxjava.lesson_3.b_transform;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * 将一个Observable分拆为一些Observables集合，它们中的每一个发射原始Observable的一个
 * 子序列
 * GroupBy 操作符将原始Observable分拆为一些Observables集合，它们中的每一个发射原始
 * Observable数据序列的一个子序列。哪个数据项由哪一个Observable发射是由一个函数判定
 * 的，这个函数给每一项指定一个Key，Key相同的数据会被同一个Observable发射。
 * RxJava实现了 groupBy 操作符。它返回Observable的一个特殊子类 GroupedObservable ，实
 * 现了 GroupedObservable 接口的对象有一个额外的方法 getKey ，这个Key用于将数据分组到指
 * 定的Observable。
 * 有一个版本的 groupBy 允许你传递一个变换函数，这样它可以在发射结
 * 果 GroupedObservable 之前改变数据项。
 * 注意： groupBy 将原始Observable分解为一个发射多个 GroupedObservable 的Observable，一
 * 旦有订阅，每个 GroupedObservable 就开始缓存数据。因此，如果你忽略这
 * 些 GroupedObservable 中的任何一个，这个缓存可能形成一个潜在的内存泄露。因此，如果你
 * 不想观察，也不要忽略 GroupedObservable 。你应该使用像 take(0) 这样会丢弃自己的缓存的
 * 操作符。
 * 如果你取消订阅一个 GroupedObservable ，那个Observable将会终止。如果之后原始的
 * Observable又发射了一个与这个Observable的Key匹配的数据， groupBy 将会为这个Key创建
 * 一个新的 GroupedObservable 。
 * groupBy 默认不在任何特定的调度器上执行。
 * Javadoc: groupBy(Func1))
 * Javadoc: groupBy(Func1,Func1))
 */
public class Operation_GroupBy {
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        init();
        step_2();

        System.out.println();
    }


    private static void init() {
        for (int i = 0; i < 9; i++) {
            list.add(i);
        }
    }


    private static void step_1() {
        Observable.from(list).groupBy(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer % 3;
            }
        }).subscribe(new Action1<GroupedObservable<Integer, Integer>>() {
            @Override
            public void call(final GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                integerIntegerGroupedObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integerIntegerGroupedObservable.getKey()+ "---->" + integer);
                    }
                });
            }
        });
    }


    //groupby 方法主要的作用就是为了进行相应的分类，并且生成相应的key用来操作
    //func1的作用主要就是用来生成key
    //func2的作用主要就是用来将相应的数据源进行变化
    private static void step_2() {
        Observable.from(list).groupBy(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
//                System.out.println("generate key + " + integer);
                integer += 100;
                return integer % 3;
            }
        }, new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer += 100;
            }
        }).subscribe(new Action1<GroupedObservable<Integer, Integer>>() {
            @Override
            public void call(final GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                integerIntegerGroupedObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integerIntegerGroupedObservable.getKey()+ "---->" + integer);
                    }
                });
            }
        });
    }
}
