package com.example.annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;

@DefaultBehavior(value = Behavior.class,getTime =  WorkDay.FRIDAY)
class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Test test = new Test();
        DefaultBehavior annotation = test.getClass().getAnnotation(DefaultBehavior.class);
        if (annotation != null) {
            WorkDay time = annotation.getTime();
            if (time.equals(WorkDay.FRIDAY))
            System.out.println("time = " + time);
            Constructor constructor = annotation.value().getConstructor(String.class, int.class);
            Behavior o = (Behavior)constructor.newInstance("zhuning", 10);
            String name = o.getName();
            System.out.println("name = " + name);
        }

        Stack<Integer> numberStack = new Stack<Integer>() {
            @Override
            public void push(Integer o) {

            }

            @Override
            public Integer pop() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };
        Collection<Number> objects = new HashSet<>();
        numberStack.popAll(objects);
    }


}
