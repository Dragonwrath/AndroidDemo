package org.joke.rxjava.lesson_1;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ObserverImpl implements Observer {
    private final static BlockingQueue<Observable> queue= new ArrayBlockingQueue<>(32);
    @Override
    public void add(Observable obj) {
        queue.add(obj);
    }

    @Override
    public void remove(Observable obj) {
        queue.remove(obj);
    }

    @Override
    public void notifyAllObservable(String string) {
        for (Observable observable : queue) {
            observable.print(string);
        }
    }
}
