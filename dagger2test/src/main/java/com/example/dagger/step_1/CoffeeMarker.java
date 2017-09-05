package com.example.dagger.step_1;


import javax.inject.Inject;

public class CoffeeMarker {
    private Heater heater;
    private Pump pump;

    @Inject public CoffeeMarker(Heater heater, Pump pump) {
        this.heater = heater;
        this.pump = pump;
    }

    public void brew() {
        heater.on();
        pump.pump();
        heater.off();
    }
}
