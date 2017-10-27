package org.joke.rxjava.lesson_3.f_observable_utility;


import java.util.ArrayList;
import java.util.List;

import rx.Notification;
import rx.Observable;
import rx.functions.Action1;


/**
 * Materialize/Dematerialize
 * Materialize 将数据项和事件通知都当做数据项发射， Dematerialize 刚好相反。
 * 一个合法的有限的Obversable将调用它的观察者的 onNext 方法零次或多次，然后调用观察者
 * 的 onCompleted 或 onError 正好一次。 Materialize 操作符将这一系列调用，包括原来
 * 的 onNext 通知和终止通知 onCompleted 或 onError 都转换为一个Observable发射的数据序
 * 列。
 * RxJava的 materialize 将来自原始Observable的通知转换为 Notification 对象，然后它返回
 * 的Observable会发射这些数据。
 * materialize 默认不在任何特定的调度器 ( Scheduler ) 上执行。
 * Javadoc: materialize())
 *
 *
 * Dematerialize 操作符是 Materialize 的逆向过程，它将 Materialize 转换的结果还原成它原
 * 本的形式。
 * dematerialize 反转这个过程，将原始Observable发射的 Notification 对象还原成
 * Observable的通知。
 * dematerialize 默认不在任何特定的调度器 ( Scheduler ) 上执行。
 * Javadoc: dematerialize())
 */
public class Operation_Materialize_Dematerialize {
    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 9; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_2();

        System.out.println();
    }

    private static void step_1() {
        Observable.from(list).materialize().subscribe(new Action1<Notification<Integer>>() {
            @Override
            public void call(Notification<Integer> integerNotification) {
                Notification.Kind kind = integerNotification.getKind();
                switch (kind) {
                    case OnNext:
                        break;
                    case OnError:
                        break;
                    case OnCompleted:
                        break;
                }
                System.out.println("kind = " + kind);
            }
        });
    }

    private static void step_2() {
        Observable.from(list).materialize().subscribe(new Action1<Notification<Integer>>() {
            @Override
            public void call(Notification<Integer> integerNotification) {
                Notification.Kind kind = integerNotification.getKind();
                switch (kind) {
                    case OnNext:
                        break;
                    case OnError:
                        break;
                    case OnCompleted:
                        break;
                }
                System.out.println("kind = " + kind);
            }
        });
    }
}
