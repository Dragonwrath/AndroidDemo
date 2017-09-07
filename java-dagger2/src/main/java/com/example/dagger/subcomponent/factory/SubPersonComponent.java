package com.example.dagger.subcomponent.factory;

import dagger.Subcomponent;

@Subcomponent(modules = PersonModule.class)
interface SubPersonComponent {
    Person inject();
}
