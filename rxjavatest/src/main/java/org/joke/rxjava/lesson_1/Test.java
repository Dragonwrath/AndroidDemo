package org.joke.rxjava.lesson_1;


public class Test {
    public static void main(String[] args) {
        Observer1 o1 = new Observer1();
        Observer2 o2 = new Observer2();

        Subject1 s1 = new Subject1();
        Subject2 s2 = new Subject2();

        s1.add(o1);
        System.out.println();
        s1.add(o2);
        System.out.println();
        s1.notifyObserver();
        System.out.println();

        s2.add(o1);
        System.out.println();
        s2.add(o2);
        System.out.println();
        s2.notifyObserver();
    }
}
