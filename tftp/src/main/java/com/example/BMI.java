package com.example;


public class BMI {
    public static void main(String[] args) {

        double value = getValue(105, 1.80, 32, true);
        System.out.println("value = " + value);

        double wifeValue = getValue(33, 1.63, 32, false);
        System.out.println("wifeValue = " + wifeValue);
    }


    private static double getValue(double weight, double height,int age, boolean isMale) {
        double sex = 0d;
        if (isMale) {
            sex = 1;
        }
        double bmi = weight / (height * height);
        return 1.2d * bmi + 0.23d * age- 5.4d - 10.8 * sex;
    }
}
