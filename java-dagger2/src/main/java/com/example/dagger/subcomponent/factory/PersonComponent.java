package com.example.dagger.subcomponent.factory;

import dagger.Component;

@Component
interface PersonComponent {
    SubPersonComponent newRequestComponent(PersonModule requestModule);
}
