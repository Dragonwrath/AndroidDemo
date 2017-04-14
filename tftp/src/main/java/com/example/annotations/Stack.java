package com.example.annotations;

import java.util.Collection;

public abstract class Stack<E> {
    abstract void push(E e);
    public abstract E pop();
    public abstract boolean isEmpty();
    public void pushAll(Iterable<E> src) {
        
        for(E e : src){
            push(e);
        }
    }
    public void popAll(Collection<? super E> dst){
        while (!isEmpty()) {
            dst.add(pop());
        }
    }
    protected interface  test{
        Thread thread = new Thread();
    }
}