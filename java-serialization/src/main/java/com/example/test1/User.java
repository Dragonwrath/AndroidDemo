package com.example.test1;

import com.google.gson.annotations.SerializedName;

public class User {
    public String name;
    public int age;
    @SerializedName(value = "email_address",alternate = {"emailAddress","email"})
    public String emailAddress;

    protected User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name --> " + name + "\n"
                + "age --> " + age + "\n"
                + "emailAddress --> " + emailAddress;
    }
}
