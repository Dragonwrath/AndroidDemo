package com.example.staticPackage;


public class MyClass {
    public static void main(String[] args) {

        try {
            try {
                throw new RuntimeException("hahah");
            } catch (RuntimeException ex) {
                throw new Throwable(ex);
            }
        } catch (Throwable tr){
            System.out.println("end" );
        }
        throw new RuntimeException("hahah");

//        Demo demo = new Demo();
//        for (int i = 0; i < 5; i++) {
//
//            ThreadTest test = new ThreadTest(demo);
//            test.start();
//        }
    }

    static class ThreadTest extends Thread {
        Demo demo ;


        public ThreadTest(Demo demo){
            this.demo = demo;
        }
        @Override
        public void run() {
            demo.test();
        }
    }
}
