package com.example.dagger.subcomponent.bind.car;


import dagger.Subcomponent;

@Subcomponent(modules = CarModule.class)
public interface CarComponent {
    @Subcomponent.Builder interface Builder {
        CarComponent requestCarComponent();
    }
}
