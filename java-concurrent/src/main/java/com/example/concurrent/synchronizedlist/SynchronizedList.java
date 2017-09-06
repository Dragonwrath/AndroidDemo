package com.example.concurrent.synchronizedlist;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedList {
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        Thread t1 = new Thread();
    }
}
