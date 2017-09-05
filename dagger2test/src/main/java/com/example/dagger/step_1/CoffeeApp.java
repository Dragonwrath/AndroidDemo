package com.example.dagger.step_1;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = CoffeeModule.class)
public interface CoffeeApp {
    CoffeeMarker marker();
}
