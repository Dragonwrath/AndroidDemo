package com.example.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    final static String i = "3";
    final static String j = "4";
    final static String x = "5";
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(i, j, x));

        System.out.println("list = " + list);
        list.remove(j);
        System.out.println("list = " + list);
    }
}
