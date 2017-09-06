package com.joker.dagger.model.person;


import dagger.Component;
import dagger.Subcomponent;

@Component(modules = PersonModule.class)
public interface PersonComponent {
    Person inject();
}
