package org.joke.rxjava.lesson_3.e_error;


import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import rx.Emitter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Catch
 * 从 onError 通知中恢复发射数据
 * Catch 操作符拦截原始Observable的 onError 通知，将它替换为其它的数据项或数据序列，
 * 让产生的Observable能够正常终止或者根本不终止。
 * 在某些ReactiveX的实现中，有一个叫 onErrorResumeNext 的操作符，它的行为与 Catch 相
 * 似。
 * RxJava将 Catch 实现为三个不同的操作符：
 * onErrorReturn
 * 让Observable遇到错误时发射一个特殊的项并且正常终止。
 * onErrorResumeNext
 * 让Observable在遇到错误时开始发射第二个Observable的数据序列。
 * onExceptionResumeNext
 * 让Observable在遇到错误时继续发射后面的数据项。
 * onErrorReturn
 * onErrorReturn 方法返回一个镜像原有Observable行为的新Observable，后者会忽略前者
 * 的 onError 调用，不会将错误传递给观察者，作为替代，它会发发射一个特殊的项并调用观
 * 察者的 onCompleted 方法。
 * Javadoc: onErrorReturn(Func1))
 *
 *
 * onErrorResumeNext
 * onErrorResumeNext 方法返回一个镜像原有Observable行为的新Observable，后者会忽略前者
 * 的 onError 调用，不会将错误传递给观察者，作为替代，它会开始镜像另一个，备用的
 * Observable。
 * Javadoc: onErrorResumeNext(Func1))
 * Javadoc: onErrorResumeNext(Observable))
 *
 *
 * onExceptionResumeNext
 * 和 onErrorResumeNext 类似， onExceptionResumeNext 方法返回一个镜像原有Observable行为
 * 的新Observable，也使用一个备用的Observable，不同的是，如果 onError 收到
 * 的 Throwable 不是一个 Exception ，它会将错误传递给观察者的 onError 方法，不会使用备用
 * 的Observable。
 * Javadoc: onExceptionResumeNext(Observable))
 *
 */
public class Operation_Catch {

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
        Observable.unsafeCreate(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onError(new Exception());
            }
        }).onErrorReturn(new Func1<Throwable, Integer>() {
            @Override
            public Integer call(Throwable throwable) {
                return -1;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

}
