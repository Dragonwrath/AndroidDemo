package org.joke.rxjava.lesson_3.a_create;

import rx.Observable;

/** Empty
*       创建一个不发射任何数据但是正常终止的Observable
*   Never
*       创建一个不发射数据也不终止的Observable
*   Throw
*       创建一个不发射数据以一个错误终止的Observable
*       这三个操作符生成的Observable行为非常特殊和受限。测试的时候很有用，有时候也用于结
*       合其它的Observables，或者作为其它需要Observable的操作符的参数。
*       RxJava将这些操作符实现为 empty ， never 和 error 。 error 操作符需要一
*       个 Throwable 参数，你的Observable会以此终止。这些操作符默认不在任何特定的调度器上
*       执行，但是 empty 和 error 有一个可选参数是Scheduler，如果你传递了Scheduler参数，它
*       们会在这个调度器上发送通知。
 */
public class Operation_Empty_Never_Throw {
    public static void main(String[] args) {
        Observable.empty();
        Observable.never();
        Observable.error(new Throwable());
    }
}
