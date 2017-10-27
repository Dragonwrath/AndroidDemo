package com.example.concurrent.hashmap;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class HashMapTest {
    public static void main(String[] args) {
        Person p1 = new Person("1");
        Person p2 = new Person("2");
        HashMap<String , Person> hashMap = new HashMap();
        HashSet<Person> set = new HashSet<>();
        boolean b = set.add(p1);
        System.out.println("b = " + b);
        b = set.add(p2);

        System.out.println("b = " + b);
        System.out.println("set.size() = " + set.size());
        System.out.println("set = " + Arrays.toString(set.toArray()));
    }
}

class Person {
    String name;
    int age;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
