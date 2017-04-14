package org.joke.rxjava.lesson_2.a_subject;

import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者。
 * 需要注意的是，PublishSubject可能会一创建完成就立刻开始发射数据（除非你可以阻止它发生），
 * 因此这里有一个风险：在Subject被创建后到有观察者订阅它之前这个时间段内，
 * 一个或多个数据可能会丢失。如果要确保来自原始Observable的所有数据都被分发，
 * 你需要这样做：或者使用Create创建那个Observable以便手动给它引入"冷"Observable的行为
 * （当所有观察者都已经订阅时才开始发射数据），或者改用ReplaySubject。
 */

public class Operator_PublishSubject {
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

        PublishSubject<String> subject = PublishSubject.create();
        // observer1 will receive all onNext and onCompleted events
        subject.subscribeOn(Schedulers.computation());
        subject.subscribe(observer1);
        subject.subscribe(observer2);
        subject.onNext("one");
        subject.onNext("two");
        // observer2 will only receive "three" and onCompleted
        subject.onNext("three");
        subject.onCompleted();
    }
}
