package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class MyClass {
    public static void main(String[] args) throws InterruptedException {

        int i = -1;
        int j = -i;
        System.out.println("j = " + j);
        i = -j;
        System.out.println("i = " + i);
    }
}
