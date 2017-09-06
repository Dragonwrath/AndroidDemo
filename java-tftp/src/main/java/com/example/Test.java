package com.example;


import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws Exception {
//        final Object obj = new Object();
//
//        new Thread(){
//            @Override
//            public void run() {
//                synchronized (obj) {
//                    try {
//                        obj.wait();
//                        System.out.println("obj = " + 1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }.start();
//
//        Thread.sleep(1000);
//        new Thread(){
//            @Override
//            public void run() {
//                synchronized (obj) {
//                    obj.notifyAll();
//                    System.out.println("obj = " + 2);
//                }
//            }
//        }.start();


        displayChar();
    }

    private static void displayChar() {
        char c = 1;
        System.out.println("(char)1 = " + (int)'?');
    }
    private static void decodeChar() throws UnsupportedEncodingException {
        float max = Math.max(3.2f, 3f);
        int testmax = (int) Math.ceil(max);
        System.out.println("max = " + testmax);

        String s = "哈";
        System.out.println("c = " + new String(s.getBytes() ,"utf-16"));
        System.out.println("c = " + new String(s.getBytes() ,"utf-8").toLowerCase());
        System.out.println("c = " + Arrays.toString(new String(s.getBytes(), "utf-16").getBytes()));
        System.out.println("c = " + Arrays.toString(new String(s.getBytes(), "utf-8").toLowerCase().getBytes()));
        String pattern = "[\ue900-\u9fbb].*";
        System.out.println("pattern = " + Arrays.toString(new String("\ue900".getBytes(),"utf-8").getBytes()));
        System.out.println("pattern = " + (new String("\u9eff".getBytes(),"utf-8")));

        Test.User str  = new Test.User("original");
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            array.add(i);
        }

        test(str,array);
        System.out.println(str);
        System.out.println("Arrays.toString(array) = " + array.get(2));
    }

    private static void test(User str,List<Integer> array) {
        str = new User("override");
        array.set(2,222222);
    }

    static class User {
        String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}