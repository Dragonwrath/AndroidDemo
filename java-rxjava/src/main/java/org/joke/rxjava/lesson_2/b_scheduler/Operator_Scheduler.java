package org.joke.rxjava.lesson_2.b_scheduler;


import rx.Scheduler;
import rx.functions.Action0;
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
