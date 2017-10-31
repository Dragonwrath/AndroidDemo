package com.example.gson.tutorials.step_1;

import com.google.gson.Gson;


public class course_1 {
    static String founderJson = "[{'name': 'Christian','flowerCount': 1}, {'name': 'Marcus', 'flowerCount': 3}, {'name': 'Norman', 'flowerCount': 2}]";
    public static void main(String[] args) {
        Gson gson = new Gson();
        Founder[] founders = gson.fromJson(founderJson, Founder[].class);
        System.out.println("founders.length = " + founders.length);
    }
    public class Founder {
        String name;
        int flowerCount;
    }
}
