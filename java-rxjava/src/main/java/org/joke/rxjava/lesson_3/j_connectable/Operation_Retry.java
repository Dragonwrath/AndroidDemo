package org.joke.rxjava.lesson_3.j_connectable;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.observables.BlockingObservable;
import rx.plugins.RxJavaSchedulersHook;

/**
 * Replay
 * 保证所有的观察者收到相同的数据序列，即使它们在Observable开始发射数据之后才订阅
 * 可连接的Observable (connectable Observable)与普通的Observable差不多，不过它并不会
 * 在被订阅时开始发射数据，而是直到使用了 Connect 操作符时才会开始。用这种方法，你可
 * 以在任何时候让一个Observable开始发射数据。
 * 如果在将一个Observable转换为可连接的Observable之前对它使用 Replay 操作符，产生的这
 * 个可连接Observable将总是发射完整的数据序列给任何未来的观察者，即使那些观察者在这
 * 个Observable开始给其它观察者发射数据之后才订阅。
 * RxJava的实现为 replay ，它有多个接受不同参数的变体，有的可以指定 replay 的最大缓存
 * 数量，有的还可以指定调度器。
 * Javadoc: replay())
 * Javadoc: replay(int))
 * Javadoc: replay(long,TimeUnit))
 * Javadoc: replay(int,long,TimeUnit))
 *
 *
 * 有一种 replay 返回一个普通的Observable。它可以接受一个变换函数为参数，这个函数接
 * 受原始Observable发射的数据项为参数，返回结果Observable要发射的一项数据。因此，这
 * 个操作符其实是 replay 变换之后的数据项。
 * Javadoc: replay(Func1))
 * Javadoc: replay(Func1,int))
 * Javadoc: replay(Func1,long,TimeUnit))
 * Javadoc: replay(Func1,int,long,TimeUnit))
 */
public class Operation_Retry {
    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_6();
        System.out.println();
        Scheduler computationScheduler = RxJavaSchedulersHook.createComputationScheduler();

    }

    //first()
    private static void step_1() {
        BlockingObservable<Integer> blocking = Observable.from(list).toBlocking();
        Integer first = blocking.first();
        System.out.println("first = " + first);
    }

    //first(func1)
    private static void step_2() {
        BlockingObservable<Integer> blocking = Observable.from(list).toBlocking();
        Integer first = blocking.first(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > -1;
            }
        });
        System.out.println("first = " + first);
    }

    //firstOrDefault(defaultValue)
    //返回默认值
    private static void step_3() {
        BlockingObservable<Integer> blocking = Observable.from(new ArrayList<Integer>()).toBlocking();
        Integer first = blocking.firstOrDefault(3);
        System.out.println("first = " + first);
    }

    //firstOrDefault(defaultValue,func1)
    //返回默认值
    private static void step_4() {
        BlockingObservable<Integer> blocking = Observable.from(new ArrayList<Integer>()).toBlocking();
        Integer first = blocking.firstOrDefault(3, new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > -1;
            }
        });
        System.out.println("first = " + first);
    }

    //single
    //如果不存在单个元素抛出异常
    private static void step_5() {
        ArrayList<Integer> iterable = new ArrayList<>(1);
        iterable.add(3);
        BlockingObservable<Integer> blocking = Observable.from(iterable).toBlocking();
        Integer first = blocking.single();
        System.out.println("first = " + first);
    }

    //single
    private static void step_6() {
        BlockingObservable<Integer> blocking = Observable.from(list).toBlocking();
        Integer first = blocking.single(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 9;
            }
        });
        System.out.println("first = " + first);
    }
}
