package org.joke.rxjava.lesson_2.a_subject;


import rx.functions.Action1;
import rx.subjects.AsyncSubject;

/*
一个AsyncSubject只在原始Observable完成后，发射来自原始Observable的最后一个值。
（如果原始Observable没有发射任何值，AsyncObject也不发射任何值）它会把这最后一个值
发射给任何后续的观察者。
然而，如果原始的Observable因为发生了错误而终止，AsyncSubject将不会发射任何数据，
只是简单的向前传递这个错误通知。
*/
public class Operator_AsyncSubject {
    public static void main(String[] args) {
        Action1<String> observer = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s = " + s);
            }
        };
        Action1<Throwable> error = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("throwable.getMessage() = " + throwable.getMessage());
            }
        };
        // observer will receive no onNext events because the subject.onCompleted() isn't called.
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.subscribe(observer);
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");

        subject.onCompleted();
        // observer will receive "three" as the only onNext event.
        AsyncSubject<String> subject1 = AsyncSubject.create();
        subject1.subscribe(observer,error);
        subject1.onNext("1-one");
        subject1.onNext("1-two");
        subject1.onError(new Throwable());

        subject1.onNext("1-three");
        subject1.onCompleted();

    }
}
