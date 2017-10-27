package org.joke.rxjava.lesson_3.f_observable_utility;

import java.util.ArrayList;
import java.util.List;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Do
 * 注册一个动作作为原始Observable生命周期事件的一种占位符
 * 你可以注册回调，当Observable的某个事件发生时，Rx会在与Observable链关联的正常通知
 * 集合中调用它。Rx实现了多种操作符用于达到这个目的。
 * RxJava实现了很多 Do 操作符的变体。
 *
 * doOnEach
 * doOnEach 操作符让你可以注册一个回调，它产生的Observable每发射一项数据就会调用它一
 * 次。你可以以 Action 的形式传递参数给它，这个Action接受一个 onNext 的变
 * 体 Notification 作为它的唯一参数，你也可以传递一个Observable给 doOnEach ，这个
 * Observable的 onNext 会被调用，就好像它订阅了原始的Observable一样。
 * Javadoc: doOnEach(Action1))
 * Javadoc: doOnEach(Observer))
 *
 * doOnNext
 * doOnNext 操作符类似于 doOnEach(Action1) ，但是它的Action不是接受一个 Notification 参
 * 数，而是接受发射的数据项。
 *
 * doOnSubscribe
 * doOnSubscribe 操作符注册一个动作，当观察者订阅它生成的Observable它就会被调用。
 * Javadoc: doOnSubscribe(Action0))
 *
 * doOnUnsubscribe
 * doOnUnsubscribe 操作符注册一个动作，当观察者取消订阅它生成的Observable它就会被调用。
 * Javadoc: doOnUnsubscribe(Action0))
 *
 * doOnCompleted
 * doOnCompleted 操作符注册一个动作，当它产生的Observable正常终止调用 onCompleted 时
 * 会被调用。
 * Javadoc: doOnCompleted(Action0))
 *
 * doOnError
 * doOnError 操作符注册一个动作，当它产生的Observable异常终止调用 onError 时会被调用
 * Javadoc: doOnError(Action0))
 *
 * doOnTerminate
 * doOnTerminate 操作符注册一个动作，当它产生的Observable终止之前会被调用，无论是正
 * 常还是异常终止。
 * Javadoc: doOnTerminate(Action0))
 *
 * finallyDo
 * finallyDo 操作符注册一个动作，当它产生的Observable终止之后会被调用，无论是正常还
 * 是异常终止。
 * Javadoc: finallyDo(Action0))
 */
public class Operation_Do {

    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 9; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_3();

        System.out.println();
    }

    //doOnEach 用用来在每次执行之前通过notification获取相应的操作方法的类型
    private static void step_1() {
        Observable.from(list).doOnEach(new Action1<Notification<? super Integer>>() {
            @Override
            public void call(Notification<? super Integer> notification) {
                Notification.Kind kind = notification.getKind();
                if (kind == Notification.Kind.OnNext)
                System.out.println("kind.toString() = " + kind.toString());
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    //底层调用onEach方法，将onError以及onCompleted 设置为Actions.empty()
    //只用来监听onNext方法
    private static void step_2() {
        Observable.from(list).doOnNext(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("dest = " + integer);
            }
        });
    }

    private static void step_3() {
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                if (!subscriber.isUnsubscribed())
                    subscriber.onNext(3);
                subscriber.unsubscribe();
            }
        }).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                System.out.println("doOnSubscribe ");
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object integer) {
                System.out.println("integer = " + integer);
            }
        });
        subscribe.unsubscribe();
    }

    private static void step_4() {
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
//                subscriber.unsubscribe();
            }
        }).doOnUnsubscribe((new Action0() {
            @Override
            public void call() {
                System.out.println("doOnSubscribe ");
            }
        })).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });

        System.out.println("subscribe is preUnsubscribe");
        subscribe.unsubscribe();
    }

}
