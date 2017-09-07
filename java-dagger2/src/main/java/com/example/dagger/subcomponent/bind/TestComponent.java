package com.example.dagger.subcomponent.bind;

import com.example.dagger.subcomponent.bind.car.Car;
import com.example.dagger.subcomponent.bind.car.CarModule;
import com.example.dagger.subcomponent.bind.person.Person;
import com.example.dagger.subcomponent.bind.person.PersonModule;

import dagger.Component;

@Component(modules = {PersonModule.class, CarModule.class})
public interface TestComponent {
    Person injectPerson();
    Car injectCar();
}
