package com.example.dagger.step_1;


import javax.inject.Inject;
import javax.inject.Singleton;

public class HaierHeater implements Heater {
    boolean hot;
    @Inject public HaierHeater() {
    }

    @Override
    public void on() {
        hot = true;
        System.out.println("Heater on");
    }

    @Override
    public void off() {
        hot = false;
        System.out.println("Heater on");
    }

    @Override
    public boolean isHot() {
        return hot;
    }
}
