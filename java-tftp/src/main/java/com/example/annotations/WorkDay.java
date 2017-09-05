package com.example.annotations;


public enum WorkDay {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THUSDAY("Thursday"),
    FRIDAY("Friday");
    WorkDay(String name){
        this.name = name;
    }
    String name;
    @Override
    public String toString() {
        return "WorkDay  "+getName();
    }

    public String getName() {
        return name;
    }
}
