package org.joke.rxjava.lesson_2.a_subject;


/**
* 当观察者订阅BehaviorSubject时，它开始发射原始Observable最近发射的数据（如果此时还
 * 没有收到任何数据，它会发射一个默认值），然后继续发射其它任何来自原始Observable的数据。
 * 然而，如果原始的Observable因为发生了一个错误而终止，BehaviorSubject将不会发射任何
 * 数据，只是简单的向前传递这个错误通知。
*/
public class Operator_BehaviorSubject {
    public static void main(String[] args) {
    }
}
