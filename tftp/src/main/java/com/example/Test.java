package com.example;


import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.Arrays;
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
    }
}
