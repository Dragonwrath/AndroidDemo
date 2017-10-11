package com.example.java.lang.ref;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class WeakReferenceTest {
    static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {

//        step_2();

        Object o = new Object();
        final HashMap<Integer, Object> map = new HashMap<>();
        map.put(0, o);
        o = null;
        System.out.println(map.get(0));
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
