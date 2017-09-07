package com.example.dagger.subcomponent.bind.person;


import dagger.Module;
import dagger.Provides;

@Module
public class PersonModule {
    String name;
    String age;

    public PersonModule(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Provides
    Person injectPerson() {
        return new Person(name,age);
    }
}
