package com.joke.mock.junit;


public class Calculator {
    public int add(int one, int another) {
        // 为了简单起见，暂不考虑溢出等情况。
        return one + another;
    }

    public int multiply(int one, int another) {
        // 为了简单起见，暂不考虑溢出等情况。
        return one * another;
    }
}
