package com.example.test3;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.lang.reflect.Modifier;

public class Test {
    public static void main(String[] args) {

        test1();
    }
    private static void test1(){
        ModifierSample sample = new ModifierSample();
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.STATIC, Modifier.PRIVATE).create();
        System.out.println("gson.toJson(sample) = " + gson.toJson(sample));
    }

    private static void test2(){
        new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                if ("finalField".equals(f.getName()))
                    return true;
                Expose expose = f.getAnnotation(Expose.class);
                if (expose != null && !expose.deserialize())
                    return true;
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                // 直接排除某个类 ，return true为排除
                return (clazz == int.class || clazz == Integer.class);
            }
        }).create();

    }
}
