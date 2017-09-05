package org.joke.rxjava.lesson_3.h_mathematical_aggregate;

//---------------------分隔线---------------------------------------------
//以下的内容属于可选包： rxjava-math 模块

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
/**
 * Average
 * 计算原始Observable发射数字的平均值并发射它
 * Average 操作符操作符一个发射数字的Observable，并发射单个值：原始Observable发射的
 * 数字序列的平均值。
 * 这个操作符不包含在RxJava核心模块中，它属于不同的 rxjava-math 模块。它被实现为四个
 * 操作符： averageDouble , averageFloat , averageInteger , averageLong 。
 * 如果原始Observable不发射任何数据，这个操作符会抛异常： IllegalArgumentException 。
 */


/**
 * Min
 * 发射原始Observable的最小值
 * Min 操作符操作一个发射数值的Observable并发射单个值：最小的那个值。
 * RxJava中， min 属于 rxjava-math 模块。
 * min 接受一个可选参数，用于比较两项数据的大小，如果最小值的数据超过一项， min 会发
 * 射原始Observable最近发射的那一项。
 * minBy 类似于 min ，但是它发射的不是最小值，而是发射Key最小的项，Key由你指定的一个
 * 函数生成。
 */
/**
 * Max
 * 发射原始Observable的最大值
 * Max 操作符操作一个发射数值的Observable并发射单个值：最大的那个值。
 * RxJava中， max 属于 rxjava-math 模块。
 * max 接受一个可选参数，用于比较两项数据的大小，如果最大值的数据超过一项， max 会发
 * 射原始Observable最近发射的那一项。
 * maxBy 类似于 max ，但是它发射的不是最大值，而是发射Key最大的项，Key由你指定的一个
 * 函数生成。
 *
 */

/**
 * Count
 * 计算原始Observable发射物的数量，然后只发射这个值
 * Count 操作符将一个Observable转换成一个发射单个值的Observable，这个值表示原始
 * Observable发射的数据的数量。
 * 如果原始Observable发生错误终止， Count 不发射数据而是直接传递错误通知。如果原始
 * Observable永远不终止， Count 既不会发射数据也不会终止。
 * RxJava的实现是 count 和 countLong 。
 * 示例代码
 * String[] items = new String[] { "one", "two", "three" };
 * assertEquals( new Integer(3), Observable.from(items).count().toBlocking().single() );
 * Javadoc: count())
 * Javadoc: countLong())
 *
 */

/**
 * Sum
 * 计算Observable发射的数值的和并发射这个和
 * Sum 操作符操作一个发射数值的Observable，仅发射单个值：原始Observable所有数值的
 * 和。
 * RxJava的实现是 sumDouble , sumFloat , sumInteger , sumLong ，它们不是RxJava核心模块
 * 的一部分，属于 rxjava-math 模块。
 * 你可以使用一个函数，计算Observable每一项数据的函数返回值的和。
 * 在 StringObservable 类（这个类不是RxJava核心模块的一部分）中有一个 stringConcat 操作
 * 符，它将一个发射字符串序列的Observable转换为一个发射单个字符串的Observable，后者
 * 这个字符串表示的是前者所有字符串的连接。
 * StringObservable 类还有一个 join 操作符，它将一个发射字符串序列的Observable转换为一
 * 个发射单个字符串的Observable，后者这个字符串表示的是前者所有字符串以你指定的分界
 * 符连接的结果。
 */
public class Operation_Average {

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
