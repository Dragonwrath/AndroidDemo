package org.joke.rxjava.lesson_1;

/*
Lesson-1
Learn the Observer Pattern
*/
public class Test {
    public static void main(String[] args) {
        ObservableImpl o1 = new ObservableImpl();
        ObservableImpl o2 = new ObservableImpl();
        ObserverImpl observer = new ObserverImpl();
        observer.add(o1);
        observer.add(o2);
        observer.notifyAllObservable("Test From Remote");
        observer.notifyAllObservable("Test From Database");
    }
}
