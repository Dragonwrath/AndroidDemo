package com.example;


public class TestForString {

    public static void main(String[] args) {
        String b = Number.getB();
        System.out.println("b = " + b);
        Number.change("C");
        b = Number.getB();
        System.out.println("b = " + b);
    }

    static class Number{
        public static String A = "A";
        public static String B;

        public static String getB(){
            return A + A;
        }
        public static void change(String s){
            A = s;
        }

    }
}
