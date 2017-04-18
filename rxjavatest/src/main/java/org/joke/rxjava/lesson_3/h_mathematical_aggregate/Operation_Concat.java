package org.joke.rxjava.lesson_3.h_mathematical_aggregate;

/**
 * Concat
 * 不交错的发射两个或多个Observable的发射物
 * Concat 操作符连接多个Observable的输出，就好像它们是一个Observable，第一个
 * Observable发射的所有数据在第二个Observable发射的任何数据前面，以此类推。
 * 直到前面一个Observable终止， Concat 才会订阅额外的一个Observable。注意：因此，如果
 * 你尝试连接一个"热"Observable（这种Observable在创建后立即开始发射数据，即使没有订阅
 * 者）， Concat 将不会看到也不会发射它之前发射的任何数据。
 * 在ReactiveX的某些实现中有一种 ConcatMap 操作符（名字可能叫 concat_all , concat_map ,
 * concatMapObserver , for , forIn/for_in , mapcat ,
 * selectConcat 或 selectConcatObserver ），他会变换原始Observable发射的数据到一个对应
 * 的Observable，然后再按观察和变换的顺序进行连接操作。
 * StartWith 操作符类似于 Concat ，但是它是插入到前面，而不是追加那些Observable的数据
 * 到原始Observable发射的数据序列。
 * Merge 操作符也差不多，它结合两个或多个Observable的发射物，但是数据可能交错，
 * 而 Concat 不会让多个Observable的发射物交错。
 * RxJava中的实现叫 concat 。
 * Javadoc: concat(Observable))
 * Javadoc: concat(Observable,Observable) )
 * 还有一个实例方法叫 concatWith ，这两者是等价
 * 的： Observable.concat(a,b) 和 a.concatWith(b) 。
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Reduce
 * 按顺序对Observable发射的每项数据应用一个函数并发射最终的值
 * Reduce 操作符对原始Observable发射数据的第一项应用一个函数，然后再将这个函数的返回
 * 值与第二项数据一起传递给函数，以此类推，持续这个过程知道原始Observable发射它的最
 * 后一项数据并终止，此时 Reduce 返回的Observable发射这个函数返回的最终值。
 * 在其它场景中，这种操作有时被称为 累积 ， 聚集 ， 压缩 ， 折叠 ， 注射 等。
 * 注意如果原始Observable没有发射任何数据， reduce 抛出异常 IllegalArgumentException 。
 * reduce 默认不在任何特定的调度器上执行。
 * Javadoc: reduce(Func2))
 * 还有一个版本的 reduce 额外接受一个种子参数。注意传递一个值为 null 的种子是合法的，
 * 但是与不传种子参数的行为是不同的。如果你传递了种子参数，并且原始Observable没有发
 * 射任何数据， reduce 操作符将发射这个种子值然后正常终止，而不是抛异常。
 * Javadoc: reduce(R,Func2))
 * 提示：不建议使用 reduce 收集发射的数据到一个可变的数据结构，那种场景你应该使
 * 用 collect 。
 * collect 与 reduce 类似，但它的目的是收集原始Observable发射的所有数据到一个可变的数
 * 据结构， collect 生成的这个Observable会发射这项数据。它需要两个参数：
 * 1. 一个函数返回可变数据结构
 * 2. 另一个函数，当传递给它这个数据结构和原始Observable发射的数据项时，适当地修改
 * 数据结构。
 * collect 默认不在任何特定的调度器上执行。
 * Javadoc: collect(Func0,Action2))
 */
public class Operation_Concat {
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

    //Contact
    //Concat 不会让多个Observable的发射物交错。
    //Merge 操作符 它结合两个或多个Observable的发射物，但是数据可能交错
    //StartWith 操作符 类似于 Concat ，但是它是插入到前面，
    //          而不是追加那些Observable的数据到原始Observable发射的数据序列。
    private static void step_1() {
        Observable.from(list).concatMap(new Func1<Integer, Observable<Long>>() {
            @Override
            public Observable<Long> call(Integer integer) {
                return Observable.timer(3, TimeUnit.SECONDS);
            }
        }).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("aLong = " + aLong);
            }
        });
    }

    //Reduce
    //Reduce 操作符对原始Observable发射数据的第一项应用一个函数，
    //          然后再将这个函数的返回值与第二项数据一起传递给函数，
    //注意如果原始Observable没有发射任何数据， reduce 抛出异常 IllegalArgumentException 。
    //      reduce 默认不在任何特定的调度器上执行。

    private static void step_2() {
        Observable.from(list).reduce(100,new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer - integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    //Collect
    //Collect 它的目的是收集原始Observable发射的所有数据到一个可变的数据结构，
    //          collect 生成的这个Observable会发射这项数据。
    //参数1:一个函数返回可变数据结构
    //参数2:另一个函数，当传递给它这个数据结构和原始Observable发射的数据项时，
    //          适当地修改数据结构。
    private static void step_3() {
        Observable.from(list).collect(new Func0<String>() {
            @Override
            public String call() {
                return "String";
            }
        }, new Action2<String, Integer>() {
            @Override
            public void call(String s, Integer integer) {
                System.out.println(s + "--[" + integer + "]" );
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s = " + s);
            }
        });
    }
}
