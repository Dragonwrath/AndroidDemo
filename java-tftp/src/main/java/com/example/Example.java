package com.example;

public class Example {

    String str = new String("good");

    char[] ch = { 'a', 'b', 'c' };

    String test;
    public static void main(String args[]) {

        Example ex = new Example();

        ex.change(ex.str, ex.ch);

        System.out.print(ex.str + " and ");

        System.out.print(ex.ch);


//        String str = "hehe";
//        ex.change(str,ex.ch);
//        System.out.println();
//        System.out.println("str = " + str);

        test(ex);
        System.out.println();
        System.out.println("str = " + ex.str);

        System.out.println("ex.test = " + ex.test);
        int i = 1;
        change(i);
        System.out.println("i = " + i);

    }

    static  void change(int i){
        i = 3;
    }

    public void change(String str, char ch[]) {

        str = "test ok";

        ch[0] = 'g';

    }

    static void test(String str){
        str = " hahahahhaha";
    }

    static void test(Example ex){
        ex.str = "hahahhahahaha";
    }
}
