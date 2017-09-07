package com.example.dagger.step_2;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface PersonComponent {
    SubPersonComponent newRequestComponent(PersonModule requestModule);
}
