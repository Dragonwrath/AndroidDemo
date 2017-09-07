package com.example.dagger.subcomponent.bind.car;


import com.example.dagger.subcomponent.bind.person.Person;

import dagger.Module;
import dagger.Provides;

@Module
public class CarModule {
    @Provides Car providePerson(Person person) {
        return new Car(person);
    }
}
