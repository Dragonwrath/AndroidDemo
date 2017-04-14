package com.example.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultBehavior {
    Class<? extends Behavior> value();
    WorkDay getTime();
}
