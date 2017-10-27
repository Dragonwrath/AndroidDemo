package com.example.dagger.subcomponent.bind;

import com.example.dagger.subcomponent.bind.car.Car;
import com.example.dagger.subcomponent.bind.person.PersonModule;

public class PersonTest {
    public static void main(String[] args) {
        buildSubComponent();
    }

    private static void buildSubComponent() {
        Car car = DaggerTestComponent.builder().personModule(new PersonModule("3","4")).build().injectCar();

        car.didi();
    }

}