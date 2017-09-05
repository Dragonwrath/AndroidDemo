package org.joke.rxjava.lesson_1;


class Observer1 implements Observer {
    @Override
    public void update(String subjectName) {
        System.out.println("Observer1--->" + subjectName);
    }
}
