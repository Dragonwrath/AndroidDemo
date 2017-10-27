package com.example;

public class MyClass {
    public static void main(String[] args) throws InterruptedException {

        int i = -1;
        int j = -i;
        System.out.println("j = " + j);
        i = -j;
        System.out.println("i = " + i);
    }
}
