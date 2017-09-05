package org.joke.rxjava.lesson_1;


class Observer2 implements Observer {
    @Override
    public void update(String name) {
        System.out.println("Observer2---->" + name);
    }
}
