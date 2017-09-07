package com.joker.dagger.model.person;


import dagger.Component;

@Component()
public interface PersonComponent {
    Person inject();
}
