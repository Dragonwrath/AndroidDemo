package com.example.concurrent.hashmap;


import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("1", "2");
        String s = map.get("1");
        System.out.println("s = " + s.hashCode());
        
        String replace = map.replace("1", "2");
        System.out.println("replace.hashCode() = " + replace.hashCode());
    }
}
