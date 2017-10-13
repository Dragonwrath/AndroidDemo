package com.example.java.lang.ref;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {

        step_3();

    }

    private static void step_3() {
        String s = "25.13442";
        final String[] split = s.split("\\.");
        final String s1 = split[1];
        if (s1.length() < 8) {
            for (int i = 0; i < 8 - s1.length(); i++) {
                s += "0";
            }
        }
        System.out.println("s1 = " + s);
    }
    private static void step_2() {
        Car car = new Car(22000,"silver");
        WeakReference<Car> weakCar = new WeakReference<Car>(car);

        int i=0;

        while(true){

            if(weakCar.get()!= null){
                i++;
                if (i >= Integer.MAX_VALUE) {
                    car = null;
                }
            }else{
//                System.out.println("Object has been collected.");
                break;
            }


        }
        System.out.println("Object is alive for "+i+" loops - "+weakCar);
        System.out.println("weakCar = " + weakCar.get());

    }

    private static void step_1() {
        String s = "fu";
        WeakReference<String> fu = new WeakReference<>(s);

//        Thread.sleep(5000);
        s = null;
        System.gc();


        s = fu.get();
        System.out.println("s = " + s);


//        final Name name = new Name(s);
//        System.out.println("name.name = " + name.name);
//
//        s = null;
//        System.out.println("name.name = " + name.name);

//        final Name name = new Name(o);
//        System.out.println(name.obj.toString());
        final WeakReference<Object> reference = new WeakReference<>(o);
        System.out.println("reference.get() = " + reference.get());
        o = null;
//        System.out.println(name.obj.toString());
        System.out.println(o +"");
        System.gc();
        System.out.println("reference.get() = " + reference.get());
    }


}

class Name {
    String name;
    Object obj;
    public Name(String s) {
        name = s;
    }

    public Name(Object obj) {
        this.obj = obj;
    }
}

class Car {
    private double price;
    private String colour;

    public Car(double price, String colour){
        this.price = price;
        this.colour = colour;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    public String toString(){
        return colour +"car costs $"+price;
    }
}
