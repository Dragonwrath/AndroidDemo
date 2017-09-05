package com.example.dagger.step_1;


public class Test {
    public static void main(String[] args) {
        CoffeeMarker marker = DaggerCoffeeApp.create().marker();
        marker.brew();

    }
}
