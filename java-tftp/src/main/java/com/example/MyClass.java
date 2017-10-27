package com.example;

public class MyClass {
    private static final Object lock = new Object();
    public static void main(String[] args) {
//        List<Thread> threads = new ArrayList<>();
//        SyncRunnable runnable = new SyncRunnable();
//        for (int i = 0; i < 4; i++) {
//            threads.add(new Thread(runnable));
//        }
//        for (Thread thread : threads) {
//            thread.start();
//        }
//        int s = getValue(2);
//        System.out.println("s = " + s);
        String str1 = "hello";
        String str2 = "he" + new String("llo");
        System.err.println(str1.equals( str2));
        new Thread().start();


        Test test = new Test(1);
        change(test);
        System.out.println("test.i = " + test.i);

        int i = 1;
        change(i);
        System.out.println("i = " + i);


        Integer integer = 1;
        Integer integer1 = new Integer(1);
        System.out.println("integer1 == integer = " +( integer1 == integer));
        System.out.println("integer1.equals(integer) = " + integer1.equals(integer));

        String s1 = "he";
        String s2 = new String("he");
        System.out.println("s2 == s1 = " + (s2==s1));
        System.out.println("s2.equals(s1) = " + s2.equals(s1));
    }
    static class Test {
        public int i;
        public Test(int i) {
            this.i = i;
        }
    }


    static void change(int i) {
        i = 3;
    }
    static void change(Test test) {
        test.i = 3;
    }

    static int getValue(int i){
        int result = 0;
        switch (i){
            case 2:
                return  result = result +i *2;
        }
        return result;
    }

    static class SyncRunnable implements Runnable {
        public int totalNum = 1000;
        @Override
        public void run() {
            while (true)
            synchronized (lock) {
                if (totalNum >0) {
                    totalNum--;
                    System.out.println("Thread Name = " + Thread.currentThread().getName() + "---->" + totalNum);
                }
            }
        }
    }
}
