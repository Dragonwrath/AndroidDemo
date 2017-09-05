package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by JunWei on 2017/2/13.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@interface Foo {
    // Field tag only annotation
}
