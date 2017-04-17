package org.joke.rxjava.lesson_3.d_combine;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.functions.Func3;

/**
 * 当两个Observables中的任何一个发射了数据时，使用一个函数结合每个Observable发射的最 
 * 近数据项，并且基于这个函数的结果发射数据。 
 * CombineLatest 操作符行为类似于 zip ，但是只有当原始的Observable中的每一个都发射了 
 * 一条数据时 zip 才发射数据。 CombineLatest 则在原始的Observable中任意一个发射了数据 
 * 时发射一条数据。当原始Observables的任何一个发射了一条数据时， CombineLatest 使用一 
 * 个函数结合它们最近发射的数据，然后发射这个函数的返回值。 
 * RxJava将这个操作符实现为 combineLatest ，它接受二到九个Observable作为参数，或者单 
 * 个Observables列表作为参数。它默认不在任何特定的调度器上执行。 
 * Javadoc: combineLatest(List,FuncN)) 
 * Javadoc: combineLatest(Observable,Observable,Func2))
 *
 *
 * withLatestFrom 
 * withLatestFrom 操作符还在开发中，不是1.0版本的一部分。类似于 combineLatest ，但是只 
 * 在单个原始Observable发射了一条数据时才发射数据。 
 */
public class Operation_CombineLatest {
    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 9; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_2();
    }

    //combineLatest会将两个Observable中发射的数据进行合并
    //并且在合并的时候，使用两个Observable发射最新的值。
    private static void step_1() {
        Observable.combineLatest(Observable.just(1), Observable.just(2,3), new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    private static void step_2(){
        Observable.combineLatest(Observable.just(1), Observable.just(2, 3), Observable.just(4, 5, 6), new Func3<Integer, Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2, Integer integer3) {
                return integer + integer2 + integer3;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }


}
