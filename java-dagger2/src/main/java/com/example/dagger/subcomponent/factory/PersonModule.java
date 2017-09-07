package com.example.dagger.subcomponent.factory;


import dagger.Module;
import dagger.Provides;

@Module
class PersonModule {
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
