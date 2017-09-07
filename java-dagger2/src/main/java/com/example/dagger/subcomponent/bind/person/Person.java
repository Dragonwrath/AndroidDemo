package com.example.dagger.subcomponent.bind.person;

import javax.inject.Inject;

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
