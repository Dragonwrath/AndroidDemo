package com.example.dagger.step_2;


import javax.inject.Inject;

import dagger.Reusable;

@Reusable
public class Person {
    @Inject String name;
    @Inject String age;

    @Inject public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getNameAndAge() {
        return name + "----" + age;
    }
}
