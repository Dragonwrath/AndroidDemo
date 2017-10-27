package com.example;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class TestA {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        long i = 0xFFFFFFFF00000000L;
////        System.out.println(Integer.parseInt(" "));
//        System.out.println(Integer.parseInt(null));
//        System.out.println(Integer.parseInt(""));
//        System.out.println(Long.toHexString(i << 32));
//        System.out.println(Long.toHexString(i >>> 32 & i));

        HashMap<Haha, Haha> map = new HashMap<>();
        Haha key = new Haha(1);
        map.put(key, new Haha(12));
        map.put(key, new Haha(2));
        System.out.println("map = " + map.get(key));

        for (Haha integer : map.keySet()) {
            System.out.println("map.get(integer).i = " + map.get(integer).i);
        }

    }

    static class Haha {
        public int i;

        public Haha(int i) {
            this.i = i;
        }

        @Override
        public int hashCode() {
            return 31;
        }
    }
}
