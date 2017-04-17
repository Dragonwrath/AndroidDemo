package org.joke.rxjava.lesson_3.c_filter;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * First
 * 只发射第一项（或者满足某个条件的第一项）数据
 * 如果你只对Observable发射的第一项数据，或者满足某个条件的第一项数据感兴趣，你可以
 * 使用 First 操作符。
 * 在某些实现中， First 没有实现为一个返回Observable的过滤操作符，而是实现为一个在当
 * 时就发射原始Observable指定数据项的阻塞函数。在这些实现中，如果你想要的是一个过滤
 * 操作符，最好使用 Take(1) 或者 ElementAt(0) 。
 * 在一些实现中还有一个 Single 操作符。它的行为与 First 类似，但为了确保只发射单个值，
 * 它会等待原始Observable终止（否则，不是发射那个值，而是以一个错误通知终止）。你可
 * 以使用它从原始Observable获取第一项数据，而且也确保只发射一项数据。
 * 在RxJava中，这个操作符被实现为 first ， firstOrDefault 和 takeFirst 。
 * 可能容易混淆， BlockingObservable 也有名叫 first 和 firstOrDefault 的操作符，它们会阻
 * 塞并返回值，不是立即返回一个Observable。
 * 还有几个其它的操作符执行类似的功能。
 * 过滤操作符
 * 只发射第一个数据，使用没有参数的 first 操作符。
 * Javadoc: first())
 *
 *
 * first(Func1)
 * 传递一个谓词函数给 first ，然后发射这个函数判定为 true 的第一项数据。
 * Javadoc: first(Func1))
 * firstOrDefault
 * firstOrDefault 与 first 类似，但是在Observagle没有发射任何数据时发射一个你在参数中
 * 指定的默认值。
 * Javadoc: firstOrDefault(T))
 *
 *
 * firstOrDefault(Func1)
 * 传递一个谓词函数给 firstOrDefault ，然后发射这个函数判定为 true 的第一项数据，如果没
 * 有数据通过了谓词测试就发射一个默认值。
 * Javadoc firstOrDefault(T, Func1))
 *
 *
 * takeFirst
 * takeFirst 与 first 类似，除了这一点：如果原始Observable没有发射任何满足条件的数
 * 据， first 会抛出一个 NoSuchElementException ， takeFist 会返回一个空的Observable（不
 * 调用 onNext() 但是会调用 onCompleted ）。
 * Javadoc: takeFirst(Func1))
 *
 *
 * single
 * single 操作符也与 first 类似，但是如果原始Observable在完成之前不是正好发射一次数
 * 据，它会抛出一个 NoSuchElementException 。
 * Javadoc: single())
 * single(Func1)
 * single 的变体接受一个谓词函数，发射满足条件的单个值，如果不是正好只有一个数据项满
 * 足条件，会以错误通知终止。
 * Javadoc: single(Func1))
 *
 *
 * singleOrDefault
 * 和 firstOrDefault 类似，但是如果原始Observable发射超过一个的数据，会以错误通知终
 * 止。
 * Javadoc: singleOrDefault(T))
 *
 *
 * singleOrDefault(T,Func1)
 * 和 firstOrDefault(T, Func1) 类似，如果没有数据满足条件，返回默认值；如果有多个数据满
 * 足条件，以错误通知终止。
 * Javadoc: singleOrDefault(Func1,T))
 * first系列的这几个操作符默认不在任何特定的调度器上执行。
 */
public class Operation_First {
    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_3();
    }

    private static void step_1(){
        Observable.just(1, 2, 3)
                .first()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }
                    @Override
                    public void onError(Throwable error) {
                        System.err.println("Error: " + error.getMessage());
                    }
                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
    }


    //takeFirst与first的区别在于，takeFirst如果不存在，会返回一个空的Observable，
    //first会抛出一个NoSuchElementException异常
    private static void step_2(){
        Observable.from(list).takeFirst(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 == 0;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    //single，接受一个谓词函数，发射满足条件的单个值，如果不是正好只有一个数据项满
    //足条件，会以错误通知终止。
    private static void step_3() {
        Observable.from(list).single(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 == 0;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }
}
