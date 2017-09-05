package com.example.test;


public abstract class Father {
    public Father() {
        System.out.println("Father constructor");
    }

    protected abstract void init();

    public static void changeWorld(){
        System.out.println("Father.changeWorld");
    }
}
