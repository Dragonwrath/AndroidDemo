package org.joke.rxjava.lesson_1;


public class ObservableImpl implements Observable {
    @Override
    public void print(String string) {
        System.out.println(this.getClass().getSimpleName() + "--->" + string);
    }
}
