package com.example.java.lang.reflect;


public class Child extends Parent {
    private String workAddress;

    public Child(String name, String age) {
        super(name, age);
    }


    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }
}
