package com.example.dagger.subcomponent.bind.car;


import com.example.dagger.subcomponent.bind.person.Person;

import javax.inject.Inject;

public class Car {
    @Inject
    private Person driver;

    @Inject public Car(Person driver) {
        this.driver = driver;
    }

    public void didi() {
        System.out.println("didi = " + driver.getNameAndAge());
    }
}
