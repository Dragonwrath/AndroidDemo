package com.joker.dagger.model.person;


import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class PersonModule {
    private String name;
    private String age;

    public PersonModule(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Provides Person providePerson() {
        return new Person(name, age);
    }
}
