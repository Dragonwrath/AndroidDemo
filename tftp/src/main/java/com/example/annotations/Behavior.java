package com.example.annotations;


import java.util.Locale;

public class Behavior {
//    public Behavior() {
//        System.out.println("New Instatnce");
//    }T
    private String name;
    private int age;

    public Behavior(String name) {
        this.name = name;
        System.out.println(String.format(Locale.getDefault(),"name %s" , name));

    }

    public Behavior(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println(String.format(Locale.getDefault(),"name %s = age %d " , name,age));
    }

    public String getName() {
        return name;
    }
}
