package org.joke.rxjava.lesson_3.e_error;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;


/**
 * Retry
 * 如果原始Observable遇到错误，重新订阅它期望它能正常终止
 * Retry 操作符不会将原始Observable的 onError 通知传递给观察者，它会订阅这个
 * Observable，再给它一次机会无错误地完成它的数据序列。 Retry 总是传递 onNext 通知给观
 * 察者，由于重新订阅，可能会造成数据项重复，如上图所示。
 *
 * RxJava中的实现为 retry 和 retryWhen 。
 * 无论收到多少次 onError 通知，无参数版本的 retry 都会继续订阅并发射原始Observable。
 * 接受单个 count 参数的 retry 会最多重新订阅指定的次数，如果次数超了，它不会尝试再次
 * 订阅，它会把最新的一个 onError 通知传递给它的观察者。
 * 还有一个版本的 retry 接受一个谓词函数作为参数，这个函数的两个参数是：重试次数和导
 * 致发射 onError 通知的 Throwable 。这个函数返回一个布尔值，如果返回 true ， retry 应该
 * 再次订阅和镜像原始的Observable，如果返回 false ， retry 会将最新的一个 onError 通知
 * 传递给它的观察者。
 *
 * retry 操作符默认在 trampoline 调度器上执行。
 *
 * Javadoc: retry())
 * Javadoc: retry(long))
 * Javadoc: retry(Func2))
 *
 * retryWhen
 * retryWhen 和 retry 类似，区别是， retryWhen 将 onError 中的 Throwable 传递给一个函
 * 数，这个函数产生另一个Observable， retryWhen 观察它的结果再决定是不是要重新订阅原
 * 始的Observable。如果这个Observable发射了一项数据，它就重新订阅，如果这个
 * Observable发射的是 onError 通知，它就将这个通知传递给观察者然后终止。
 *
 * retryWhen 默认在 trampoline 调度器上执行，你可以通过参数指定其它的调度器。
 *
 * Javadoc: retryWhen(Func1))
 * Javadoc: retryWhen(Func1,Scheduler))
 */
public class Operation_Retry {

    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 9; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_1();

        System.out.println();
    }

    private static void step_1() {
        Observable.unsafeCreate(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onError(new Exception("hhahahaha"));
            }
        }).retry(new Func2<Integer, Throwable, Boolean>() {
            @Override
            public Boolean call(Integer integer, Throwable throwable) {
                System.out.println("throwable = " + throwable.getMessage());
                return false;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    private static void step_2() {
        Observable.unsafeCreate(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onError(new Exception("hhahahaha"));
            }
        }).retryWhen(new Func1<Observable<? extends Throwable>, Observable<Long>>() {
            @Override
            public Observable<Long> call(Observable<? extends Throwable> observable) {
                return Observable.timer(3, TimeUnit.SECONDS);
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e.getMessage() = " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {

            }
        });
    }

}
