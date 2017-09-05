package org.joke.rxjava.lesson_1;


import java.util.ArrayList;

class Subject2 implements Subject {
    private final static String NAME = Subject2.class.getSimpleName();
    private final ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void add(Observer observer) {
        observers.add(observer);
        notifyObserver();
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(NAME);
        }
    }
}
