package com.example.dagger.step_2;


import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = PersonModule.class)
public interface PersonComponent {
    Person inject();
}
