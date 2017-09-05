package org.joke.rxjava.lesson_3.c_filter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * TakeLast
 * 发射Observable发射的最后N项数据
 * 使用 TakeLast 操作符修改原始Observable，你可以只发射Observable'发射的后N项数据，忽
 * 略前面的数据。
 * taskLast.n
 * 使用 takeLast 操作符，你可以只发射原始Observable发射的后N项数据，忽略之前的数据。
 * 注意：这会延迟原始Observable发射的任何数据项，直到它全部完成。
 * takeLast 的这个变体默认不在任何特定的调度器上执行。
 * Javadoc: takeLast(int))
 * takeLast.t
 * 还有一个 takeLast 变体接受一个时长而不是数量参数。它会发射在原始Observable的生命周
 * 期内最后一段时间内发射的数据。时长和时间单位通过参数指定。
 * 注意：这会延迟原始Observable发射的任何数据项，直到它全部完成。
 * takeLast 的这个变体默认在 computation 调度器上执行，但是你可以使用第三个参数指定其
 * 它的调度器。
 * takeLastBuffer
 * 还有一个操作符叫 takeLastBuffer ，它和 takeLast 类似，，唯一的不同是它把所有的数据项
 * 收集到一个 List 再发射，而不是依次发射一个。
 * Javadoc: takeLastBuffer(int))
 * Javadoc: takeLastBuffer(long,TimeUnit))
 * Javadoc: takeLastBuffer(long,TimeUnit,Scheduler))
 * Javadoc: takeLastBuffer(int,long,TimeUnit))
 * Javadoc: takeLastBuffer(int,long,TimeUnit,Scheduler))
 */
public class Operation_TakeLast {
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

    private static void step_1() {
        Observable.from(list).takeLast(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }
}
