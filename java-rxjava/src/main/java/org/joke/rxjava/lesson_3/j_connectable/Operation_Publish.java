package org.joke.rxjava.lesson_3.j_connectable;


import java.util.ArrayList;
import java.util.List;

/**
 * Publish
 * 将普通的Observable转换为可连接的Observable
 * 可连接的Observable (connectable Observable)与普通的Observable差不多，不过它并不会
 * 在被订阅时开始发射数据，而是直到使用了 Connect 操作符时才会开始。用这种方法，你可
 * 以在任何时候让一个Observable开始发射数据。
 * RxJava的实现为 publish 。
 * Javadoc: publish())
 *
 * 有一个变体接受一个函数作为参数。这个函数用原始Observable发射的数据作为参数，产生
 * 一个新的数据作为 ConnectableObservable 给发射，替换原位置的数据项。实质是在签名的基
 * 础上添加一个 Map 操作。
 * Javadoc: publish(Func1))
 */
public class Operation_Publish {
    private static List<Integer> list = new ArrayList<>();

    private static void init() {
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        init();
        step_1();
        System.out.println();
    }

    private static void step_1() {
    }
}
