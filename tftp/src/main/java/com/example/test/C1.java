package com.example.test;

class MyExc1 extends Exception {}
class MyExc2 extends Exception {}
class MyExc3 extends MyExc2 {}

public class C1 {
    public static void main(String[] args) throws Exception {
        try {
            System.out.print(1);
            q();
        }
        catch (Exception i) {
            throw new MyExc2();
        }
        finally {
            System.out.print(2);
            throw new MyExc1();
        }
    }

    static void q() throws Exception {
        try {
            throw new MyExc1();
        }
        catch (Exception y) {
        }
        finally {
            System.out.print(3);
            throw new Exception();
        }
    }
}