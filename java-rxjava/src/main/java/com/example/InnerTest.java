package com.example;

/**
 * Created by JunWei on 2017/2/21.
 */

class Outer {
    public static class StaticInner {
    }
    public class NonStaticInner {
    }
}
class InnerTest {
    public static void main(String[] args) {
        Outer outer = new Outer();
        //对于非静态内部类
        Outer.NonStaticInner nonStaticInner = outer.new NonStaticInner();
        //对于静态内部类
        Outer.StaticInner staticInner = new Outer.StaticInner();
    }
}
