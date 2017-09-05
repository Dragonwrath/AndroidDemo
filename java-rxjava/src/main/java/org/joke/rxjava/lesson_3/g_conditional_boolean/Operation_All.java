package org.joke.rxjava.lesson_3.g_conditional_boolean;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * All
 * 判定是否Observable发射的所有数据都满足某个条件
 * 传递一个谓词函数给 All 操作符，这个函数接受原始Observable发射的数据，根据计算返回
 * 一个布尔值。 All 返回一个只发射一个单个布尔值的Observable，如果原始Observable正常
 * 终止并且每一项数据都满足条件，就返回true；如果原始Observable的任何一项数据不满足条
 * 件就返回False。
 * RxJava将这个操作符实现为 all ，它默认不在任何特定的调度器上执行。
 * Javadoc: all(Func1))
 */

public class Operation_All {

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


    //all方法是用来判断输入的数据是否满足条件
    private static void step_1() {
        Observable.from(list).all(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 0;
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println("aBoolean = " + aBoolean);
            }
        });
    }

}
