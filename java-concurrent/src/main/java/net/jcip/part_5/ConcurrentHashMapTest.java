package net.jcip.part_5;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        Enumeration<String> keys = map.keys();
        boolean b = keys.hasMoreElements();

        HashMap hashMap = new HashMap();

        TreeSet<String> set = new TreeSet<>();
    }
}
