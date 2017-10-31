package com.example.gson.tutorials.step_1;

import com.google.gson.Gson;

public class course_7 {
    public static void main(String[] args) {
        UserCircular child = new UserCircular("1", "1@example.com", 33, true);
        UserCircular parent = new UserCircular("0", "1@example.com", 33, true);
        UserCircular target = new UserCircular("2", "1@example.com", 33, true,child,parent);
        Gson gson = new Gson();
        String s = gson.toJson(target);
        System.out.println("s = " + s);
        s = "{\"name\":\"2\",\"email\":\"1@example.com\",\"age\":33,\"isDeveloper\":true,\"child\":{\"name\":\"1\",\"email\":\"1@example.com\",\"age\":33,\"isDeveloper\":true," +
                "\"child\":{\"name\":\"1\",\"email\":\"1@example.com\",\"age\":33,\"isDeveloper\":true},\"parent\":{\"name\":\"0\",\"email\":\"1@example.com\",\"age\":33,\"isDeveloper\":true}},\"parent\":{\"name\":\"0\",\"email\":\"1@example.com\",\"age\":33,\"isDeveloper\":true}}\n";
        UserCircular userCircular = gson.fromJson(s, UserCircular.class);
        System.out.println("userCircular = " + userCircular.toString());
    }
}
class UserCircular {
    String name;
    String email;
    int age;
    boolean isDeveloper;

    // references to child & parent node

    UserCircular child;
    UserCircular parent;

    public UserCircular(String name, String email, int age, boolean isDeveloper) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.isDeveloper = isDeveloper;
    }

    public UserCircular(String name, String email, int age, boolean isDeveloper, UserCircular child) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.isDeveloper = isDeveloper;
        this.child = child;
    }

    public UserCircular(String name,  String email, int age,boolean isDeveloper, UserCircular child, UserCircular parent) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.isDeveloper = isDeveloper;
        this.child = child;
        this.parent = parent;
    }
}