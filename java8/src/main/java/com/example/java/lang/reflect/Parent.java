package com.example.java.lang.reflect;


public class Parent {
    protected String name;
    protected String age;

    public Parent(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private void parentOnly() {
        System.out.println("Parent.parentOnly");
    }
}
