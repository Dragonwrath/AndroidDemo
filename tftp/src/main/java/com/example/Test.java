package com.example;



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
    }
}
