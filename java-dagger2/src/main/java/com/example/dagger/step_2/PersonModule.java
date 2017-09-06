package com.example.dagger.step_2;


import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
class PersonModule {
    String name;
    String age;

    public PersonModule(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Provides Person injectPerson() {
        return new Person(name,age);
    }
}
