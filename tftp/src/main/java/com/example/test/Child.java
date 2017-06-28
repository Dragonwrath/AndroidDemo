package com.example.test;


public class Child extends Father {
    public String name = "hahha";

    public Child() {
        super();
        init();
        System.out.println("Child constructor");
    }

    @Override
    protected void init() {
        System.out.println("name = " + name);
    }

    public static void changeWorld() {
        System.out.println("Child.changeWorld");
    }

    static class Test {
        int i = 3;
        void print() {
            System.out.println("i = " + i);
        }
    }
}
