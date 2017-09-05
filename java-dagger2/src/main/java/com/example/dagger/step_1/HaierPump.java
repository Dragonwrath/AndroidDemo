package com.example.dagger.step_1;


import javax.inject.Inject;

public class HaierPump implements Pump {

    Heater heater;

    @Inject public HaierPump(Heater heater) {
        this.heater = heater;
    }

    @Override
    public void pump() {
        if (heater.isHot()) {
            System.out.println("Pump pump");
        }
    }
}
