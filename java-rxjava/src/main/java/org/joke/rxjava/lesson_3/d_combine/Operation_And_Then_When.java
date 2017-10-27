package org.joke.rxjava.lesson_3.d_combine;


import java.util.ArrayList;
import java.util.List;


//它们属于 rxjava-joins 模块，不是核心RxJava包的一部分。

/**
 * 使用Pattern和Plan作为中介，将两个或多个Observable发射的数据集合并到一起 
 * And/Then/When操作符组合的行为类似于 zip ，但是它们使用一个中间数据结构。接受两个 
 * 或多个Observable，一次一个将它们的发射物合并到 Pattern 对象，然后操作那 
 * 个 Pattern 对象，变换为一个 Plan 。随后将这些 Plan 变换为Observable的发射物。 
 * 它们属于 rxjava-joins 模块，不是核心RxJava包的一部分。 
 */
public class Operation_And_Then_When {
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

    }
}
