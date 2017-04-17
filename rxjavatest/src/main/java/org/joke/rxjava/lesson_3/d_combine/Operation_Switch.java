package org.joke.rxjava.lesson_3.d_combine;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 Switch
 将一个发射多个Observables的Observable转换成另一个单独的Observable，后者发射那些
 Observables最近发射的数据项
 个， Switch 返回的这个Observable取消订阅前一个发射数据的Observable，开始发射最近的
 Observable发射的数据。注意：当原始Observable发射了一个新的Observable时（不是这个
 新的Observable发射了一条数据时），它将取消订阅之前的那个Observable。这意味着，在
 后来那个Observable产生之后到它开始发射数据之前的这段时间里，前一个Observable发射
 的数据将被丢弃（就像图例上的那个黄色圆圈一样）。
 Java将这个操作符实现为 switchOnNext 。它默认不在任何特定的调度器上执行。
 Javadoc: switchOnNext(Observable))
 */
public class Operation_Switch {
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
        Observable.from(list).switchIfEmpty(Observable.just(1))
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = " + integer);
                    }
                });
    }
}
