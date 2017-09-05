package org.joke.rxjava.lesson_1;


interface Subject {
    void add(Observer observer);
    void remove(Observer observer);
    void notifyObserver();
}
