package org.joke.rxjava.lesson_3.b_transform;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;


/**
 * Scan
 * 连续地对数据序列的每一项应用一个函数，然后连续发射结果
 * Scan 操作符对原始Observable发射的第一项数据应用一个函数，然后将那个函数的结果作为
 * 自己的第一项数据发射。它将函数的结果同第二项数据一起填充给这个函数来产生它自己的
 * 第二项数据。它持续进行这个过程来产生剩余的数据序列。这个操作符在某些情况下被叫
 * 做 accumulator 。
 * scan 操作符的变体，你可以传递一个种子值给累加器函数的第一次调用（Observable
 * 发射的第一项数据）。如果你使用这个版本， scan 将发射种子值作为自己的第一项数据。注
 * 意：传递 null 作为种子值与不传递是不同的， null 种子值是合法的。
 * 这个操作符默认不在任何特定的调度器上执行。
 */
public class Operation_Scan {

    public static void main(String[] args) {
        init();
        step_1();
    }

    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    //scan方法，可以用来计算集合中的所有数据的和，或者一些条件之后的计算
    //第一个参数都已设置为Sum的初始化的值
    //Func2 的第一个参数，我们可以理解为Sum 第二个参数是第二次输入的参数
    private static void step_1(){
        Observable.from(list).scan(100,new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer +integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

}
