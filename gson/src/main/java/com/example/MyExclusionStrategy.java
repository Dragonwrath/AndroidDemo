package com.example;

import com.example.Foo;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

class MyExclusionStrategy implements ExclusionStrategy {
    private final Class<?> typeToSkip;

    public MyExclusionStrategy(Class<?> typeToSkip) {
        this.typeToSkip = typeToSkip;
    }

    public boolean shouldSkipClass(Class<?> clazz) {
        return (clazz == typeToSkip);
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(Foo.class) != null;
    }
}
