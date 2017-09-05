package com.example.interpolator;


import com.sun.javafx.geom.Matrix3f;

public class Test {
    public static void main(String[] args) {
        long t1 = System.nanoTime();
        long t2 = 0;
        float f = 1.0f;
        if (f == 1f){
            System.out.println("System.nanoTime() - t1 = " + (System.nanoTime() - t1));
            t2 =  System.nanoTime();
        }
        if (Float.compare(f, 1.0f) == 0) {
            System.out.println("(System.nanoTime() - t1) = " + (System.nanoTime() - t2));
        }
    }


}
