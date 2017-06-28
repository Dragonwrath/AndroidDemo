package com.example.test;

enum StartType {
    LITTLE(0), MIDDLE(1), BIG(2);

    StartType(int i){
        this.i = i;
    }

    public int  i ;

    static StartType getType(int i){
        return StartType.values()[i];
    }

}