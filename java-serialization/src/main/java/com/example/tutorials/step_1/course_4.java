package com.example.tutorials.step_1;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;


public class course_4 {
    public static void main(String[] args) {
        ExclusionStrategy strategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                boolean result =false;
                Collection<Annotation> annotations = f.getAnnotations();
                for (Annotation annotation : annotations) {
                    Class<? extends Annotation> aClass = annotation.getClass();
                    if (aClass.equals(Skip.class)){
                        result = true;
                        break;
                    }
                }

                return result;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                String name = clazz.getName();
//                System.out.println("Class<?> = " + name);

                return false;
            }
        };
        GsonBuilder gsonBuilder = new GsonBuilder().setExclusionStrategies(strategy);
        gsonBuilder.excludeFieldsWithModifiers(Modifier.PRIVATE);
        Gson gson = gsonBuilder.create();
        UserDate user = new UserDate("Norman", "norman@futurestud.io", 26, true);

        float negativeInfinity = Float.NEGATIVE_INFINITY;
        System.out.println("negativeInfinity = " + negativeInfinity);
        float positiveInfinity = Float.POSITIVE_INFINITY;
        System.out.println("positiveInfinity = " + positiveInfinity);
        String s = gson.toJson(user);
        System.out.println("s = " + s);

    }

}
class UserDate {
    private String _name;
    private String email;
    private boolean isDeveloper;
    private int age;
    @Skip
    private transient String pattern = "yyyy-MM-dd-hh-mm-ss";
    @Skip
    private Date registerDate = new Date();
    @Skip
    private String time= new SimpleDateFormat(pattern, Locale.getDefault()).format(registerDate);

    public UserDate(String _name, String email, int age, boolean isDeveloper) {
        this._name = _name;
        this.email = email;
        this.age = age;
        this.isDeveloper = isDeveloper;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Skip{
}
