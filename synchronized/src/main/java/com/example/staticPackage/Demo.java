package com.example.staticPackage;

class Demo implements Test{

    @Override
    public final synchronized void test() {
        System.out.println(Thread.currentThread().getName()+"-------"+hashCode());
    }
}
