package org.joke.rxjava.lesson_2.a_subject;

import rx.functions.Action1;
import rx.subjects.ReplaySubject;

/**
 * ReplaySubject会发射所有来自原始Observable的数据给观察者，无论它们是何时订阅的。
 * 也有其它版本的ReplaySubject，在重放缓存增长到一定大小的时候或过了一段时间后会丢弃旧的数据
 * （原始Observable发射的）。如果你把ReplaySubject当作一个观察者使用，注意不要从多个线程中调用它的onNext方法
 * （包括其它的on系列方法），这可能导致同时（非顺序）调用，这会违反Observable协议，
 * 给Subject的结果增加了不确定性。
 */
public class Operator_RelaySubject {
    public static void main(String[] args) {
        Action1<String> observer1 = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s1 = " + s);
            }
        };
        Action1<String> observer2 = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s2 = " + s);
            }
        };

        ReplaySubject<String> subject = ReplaySubject.create();
        subject.onNext("one");
        subject.onNext("two");
        subject.subscribe(observer1);
        subject.subscribe(observer2);
        subject.onNext("three");
        subject.onCompleted();

        // both of the following will get the onNext/onCompleted calls from above
    }
}
