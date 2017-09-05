package org.joke.rxjava.lesson_3.g_conditional_boolean;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/** Contains
 * 判定一个Observable是否发射一个特定的值
 * 给 Contains 传一个指定的值，如果原始Observable发射了那个值，它返回的Observable将发
 * 射true，否则发射false。
 * 相关的一个操作符 IsEmpty 用于判定原始Observable是否没有发射任何数据。
 * contains 默认不在任何特定的调度器上执行。
 * Javadoc: contains(Object))
 *
 *
 * RxJava中还有一个 exists 操作符，它通过一个谓词函数测试原始Observable发射的数据，
 * 只要任何一项满足条件就返回一个发射true的Observable，否则返回一个发射false的
 * Observable。
 * exists 默认不在任何特定的调度器上执行。
 * Javadoc: exists(Func1))
 *
 *
 * isEmpty 默认不在任何特定的调度器上执行。
 * Javadoc: isEmpty())
 *
 *
 * DefaultIfEmpty
 * 发射来自原始Observable的值，如果原始Observable没有发射任何值，就发射一个默认值
 * DefaultIfEmpty 简单的精确地发射原始Observable的值，如果原始Observable没有发射任何
 * 数据正常终止（以 onCompleted d的形式）， DefaultIfEmpty 返回的Observable就发射一个你
 * 提供的默认值。
 * RxJava将这个操作符实现为 defaultIfEmpty 。它默认不在任何特定的调度器上执行。
 * Javadoc: defaultIfEmpty(T))
 *
 *
 * 还有一个新的操作符 switchIfEmpty ，不在RxJava 1.0.0版中，它和 defaultIfEmtpy 类似，不
 * 同的是，如果原始Observable没有发射数据，它发射一个备用Observable的发射物。
 * SequenceEqual
 * 判定两个Observables是否发射相同的数据序列。
 * 传递两个Observable给 SequenceEqual 操作符，它会比较两个Observable的发射物，如果两
 * 个序列是相同的（相同的数据，相同的顺序，相同的终止状态），它就发射true，否则发射
 * false。
 * 它还有一个版本接受第三个参数，可以传递一个函数用于比较两个数据项是否相同。
 * 这个操作符默认不在任何特定的调度器上执行。
 * Javadoc: sequenceEqual(Observable,Observable))
 * Javadoc: sequenceEqual(Observable,Observable,Func2))
 * SkipUntil
 * 丢弃原始Observable发射的数据，直到第二个Observable发射了一项数据
 * SkipUntil 订阅原始的Observable，但是忽略它的发射物，直到第二个Observable发射了一
 * 项数据那一刻，它开始发射原始Observable。
 * RxJava中对应的是 skipUntil ，它默认不在任何特定的调度器上执行。
 * Javadoc: skipUntil(Observable))
 * SkipWhile
 * 丢弃Observable发射的数据，直到一个指定的条件不成立
 * SkipWhile 订阅原始的Observable，但是忽略它的发射物，直到你指定的某个条件变为false
 * 的那一刻，它开始发射原始Observable。
 * skipWhile 默认不在任何特定的调度器上执行。
 * Javadoc: skipWhile(Func1))
 * TakeUntil
 * 当第二个Observable发射了一项数据或者终止时，丢弃原始Observable发射的任何数据
 * TakeUntil 订阅并开始发射原始Observable，它还监视你提供的第二个Observable。如果第
 * 二个Observable发射了一项数据或者发射了一个终止通知， TakeUntil 返回的Observable会
 * 停止发射原始Observable并终止。
 * RxJava中的实现是 takeUntil 。注意：第二个Observable发射一项数据或一个 onError 通知
 * 或一个 onCompleted 通知都会导致 takeUntil 停止发射数据。
 * takeUntil 默认不在任何特定的调度器上执行。
 * Javadoc: takeUntil(Observable))
 * 还有一个版本的 takeUntil ，不在RxJava 1.0.0版中，它使用一个谓词函数而不是第二个
 * Observable来判定是否需要终止发射数据，它的行为类似于 takeWhile 。
 * Javadoc: takeUntil(Func1))
 * TakeWhile
 * 发射Observable发射的数据，直到一个指定的条件不成立
 * TakeWhile 发射原始Observable，直到你指定的某个条件不成立的那一刻，它停止发射原始
 * Observable，并终止自己的Observable。
 * RxJava中的 takeWhile 操作符返回一个镜像原始Observable行为的Observable，直到某一项
 * 数据你指定的函数返回 false 那一刻，这个新的Observable发射 onCompleted 终止通知。
 * takeWhile 默认不在任何特定的调度器上执行。
 * Javadoc: takeWhile(Func1))
 */
public class Operation_Contains {

    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 9; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_9();
    }

    //contains
    //用来判断是否存在相应的Observable发送过来的数据中是否存在指定的元素
    private static void step_1() {
        Observable.from(list).contains(8)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        System.out.println("aBoolean = " + aBoolean);
                    }
                });
    }

    //exists
    //通过指定的Func1用来判断发送过来的数据是否存在相应的数据
    private static void step_2() {
        Observable.from(list).exists(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 2;
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println("aBoolean = " + aBoolean);
            }
        });
    }

    //isEmpty
    //用来判断发送的数据是否为空
    private static void step_3() {
        Observable.empty().isEmpty().subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println("aBoolean = " + aBoolean);
            }
        });
    }

    //defaultIfEmpty
    //如果接收的数据为null则发送默认值
    private static void step_4() {
        Observable<Object> observable = Observable.empty().defaultIfEmpty(3);
        observable.subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println("o.getClass() = " + o);
            }
        });
    }

    //SequenceEqual
    //判定两个Observables是否发射相同的数据序列。
    private static void step_5() {
        Observable.sequenceEqual(Observable.from(list),Observable.from(list))
        .subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println("aBoolean = " + aBoolean);
            }
        });

    }

    //SkipUntil
    //丢失原始数据，知道第二个Observable开始发射数据
    private static void step_6() {
        Observable.from(list).skipUntil(Observable.just(1)).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    //SkipWhile
    //直到Observable的某个条件不满足的时候，再发送数据，否则就丢弃数据
    private static void step_7() {
        Observable.from(list).skipWhile(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return false;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    //TakeUntil
    //直到Observable的某个条件满足的时候，Observable停止发送数据
    private static void step_8() {
        Observable.from(list).takeUntil(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return false;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    //TakeWhile
    //直到Observable的某个条件不满足的时候，Observable停止发送数据
    private static void step_9() {
        Observable.from(list).takeWhile(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return true;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }
}
