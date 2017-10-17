package com.example.java.lang.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MethodTest {
    public static void main(String[] args) throws Exception{
        Class clazz = Child.class;
        Constructor  con = clazz.getConstructor(String.class,String.class);
        Child child = (Child) con.newInstance("xiaojiahe", "qigai");
        clazz = invokeSpecifyMethod("setName", clazz);
        final Method setName = clazz.getDeclaredMethod("setName",String.class);
        setName.invoke(child,"zhuning");
        System.out.println("child = " + child.getName());

        final Method only = clazz.getDeclaredMethod("parentOnly");
        only.setAccessible(true);
        final Object invoke = only.invoke(child, null);

    }

    private static Class invokeSpecifyMethod(String methodName, Class clazz){
        Class needClass = null;
        final Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().contains(methodName)) {
                return clazz;
            }
        }
        final Class superclass = clazz.getSuperclass();
        if (superclass != null)
            needClass = invokeSpecifyMethod(methodName, superclass);
        return needClass;
    }
}
