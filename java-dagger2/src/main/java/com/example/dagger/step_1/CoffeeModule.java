package com.example.dagger.step_1;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CoffeeModule {
    @Singleton @Provides Heater provideHeater() {
        return new HaierHeater();
    }

    @Provides Pump providePump(HaierPump pump) {
        return pump;
    }
}
