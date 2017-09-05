package com.example;


import java.util.*;
class PrintData {
    static void print(Enumeration e) {
        while(e.hasMoreElements())
            System.out.println(
                    e.nextElement().toString());
    }
}
class Enumerators2 {
    public static void main(String[] args) {
//        TreeSet<Mouse> mouses = new TreeSet<>();
//        for (int i = 0; i < 10 ; i++) {
//            mouses.add(new Mouse(i));
//
//        }
//        Iterator<Mouse> iterator = mouses.iterator();
//        while( iterator.hasNext()){
//            System.out.println("iterator.next() = " + iterator.next());
//        }
//        System.out.println("mouses = " + mouses.size());

        HashMap<String,Mouse>  mouseGroup = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            mouseGroup.put("0",new Mouse(i));
        }
        int size = mouseGroup.size();
        System.out.println("size = " + size);
        mouseGroup.get("0");

        try {
        } catch (RuntimeException e){
            e.printStackTrace();
        }

    }

    private static class Mouse implements Comparable<Mouse> {
        int i;
        public Mouse(int i) {
            this.i = i;
        }

        @Override
        public int hashCode() {
            return i*17+3;
        }

        @Override
        public String toString() {
            return "Mouse-->"+i;
        }


        @Override
        public int compareTo(Mouse o) {
            return o.i-i;
        }
    }

    private static class Hamster {
        int i;
        public Hamster(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "Hamster-->"+i;
        }
    }
} ///:~

