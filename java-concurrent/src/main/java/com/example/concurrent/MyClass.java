package com.example.concurrent;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class MyClass {

    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<>();
        LinkedList<String> ll = new LinkedList<>();
        al.add("3");al.add("2");al.add("4");
        ll.add("3");ll.add("2");ll.add("4");
        long t1 = System.nanoTime();
        al.add("1");
        long t2 = System.nanoTime();

        System.out.println("(t2-t1 = " + (t2-t1));

        t1 = System.nanoTime();
        ll.add("1");
        t2 = System.nanoTime();

        System.out.println("(t2-t1 = " + (t2-t1));

        System.out.println("------------------------------------");

        t1 = System.nanoTime();
        ll.indexOf("1");
        t2 = System.nanoTime();

        System.out.println("(t2-t1 = " + (t2-t1));

        t1 = System.nanoTime();
        ll.indexOf("1");
        t2 = System.nanoTime();

        System.out.println("(t2-t1 = " + (t2-t1));

        System.out.println("------------------------------------");
    }
}
