package com.example;

public class ShortCircuit {
    public static void main(String[] args) {
        for (int i = 0; i < 1024; i++) {
            System.out.print(String.format("i = %-5s" ,String.valueOf((char)i)));
            if (i % 5== 0)
                System.out.println();
        }
//        int i = -1;
//        i >>>= 10;
//        System.out.println(Integer.toHexString(i)+"----"+i);
//        long l = -1;
//        l >>>= 10;
//        System.out.println(Long.toHexString(l)+"----"+l);
//        short s = -1;
//        System.out.println("s = " + Integer.toHexString(s));
//        s >>>= 10;
//        System.out.println(Integer.toHexString(s)+"----"+s);
//        byte b = -1;
//        b >>>= 10;
//        System.out.println(Integer.toHexString(b)+"----"+b);
//        Math.E
    }
}
