package org.joke.rxjava.lesson_2.b_scheduler;


import rx.Emitter;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.observables.AsyncOnSubscribe;
import rx.schedulers.Schedulers;

public class Operator_Scheduler {
    static Scheduler.Worker worker = Schedulers.newThread().createWorker();
    static int i = 10;
    static Action0 observer = new Action0() {
        @Override
        public void call() {
            if (i < 0)
                System.out.println("complete ");
            else{
                i--;
                System.out.println("i = " + i);
                worker.schedule(this);
            }
        }
    };
    public static void main(String[] args) {
        worker.schedule(observer);
        if (worker.isUnsubscribed())
            worker.unsubscribe();


    }
}
