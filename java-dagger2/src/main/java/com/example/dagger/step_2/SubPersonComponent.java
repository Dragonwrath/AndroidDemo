package com.example.dagger.step_2;

import dagger.Subcomponent;

@Subcomponent(modules = PersonModule.class)
public interface SubPersonComponent {
    Person inject();
}
