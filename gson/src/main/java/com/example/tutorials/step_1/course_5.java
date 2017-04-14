package com.example.tutorials.step_1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by JunWei on 2017/2/14.
 */

public class course_5 {
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeSpecialFloatingPointValues();
        Gson gson = builder.create();
        UserFloat user = new UserFloat("Norman", Float.POSITIVE_INFINITY);

        String usersJson = gson.toJson(user); // will throw an exception
        System.out.println("usersJson = " + usersJson);
    }
}
class UserFloat {
    String name;
    Float weight;

    public UserFloat(String name, Float weight) {
        this.name = name;
        this.weight = weight;
    }
}