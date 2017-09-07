package com.example.dagger.subcomponent.bind.person;

import dagger.Subcomponent;

@Subcomponent(modules = PersonModule.class)
public interface SubPersonComponent {
    @Subcomponent.Builder
    interface Builder {
        SubPersonComponent requestSubPersonComponent();
    }
}
