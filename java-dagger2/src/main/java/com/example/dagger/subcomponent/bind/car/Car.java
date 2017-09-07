package com.example.dagger.subcomponent.bind.car;


import javax.inject.Inject;

public class Car {
    @Inject
    com.example.dagger.subcomponent.bind.person.Person driver;

    @Inject public Car(com.example.dagger.subcomponent.bind.person.Person driver) {
        this.driver = driver;
    }

    public void didi() {
        System.out.println("didi = " + driver.getNameAndAge());
    }
}
