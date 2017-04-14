package org.joke.rxjava.lesson_1;


public interface Observer {
    void add(Observable obj);
    void remove(Observable obj);
    void notifyAllObservable(String string);

}
