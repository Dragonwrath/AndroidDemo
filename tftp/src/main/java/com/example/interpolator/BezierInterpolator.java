package com.example.interpolator;


public class BezierInterpolator implements TimeInterpolator {

    @Override
    public float getInterpolation(float input) {
        return (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
    }
}
