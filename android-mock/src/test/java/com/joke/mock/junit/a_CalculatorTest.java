package com.joke.mock.junit;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
 * @BeforeClass 在跑一个测试类的所有测试方法之前，会执行一次被@BeforeClass修饰的方法
 * @AfterClass  执行完所有测试方法之后，会执行一遍被@AfterClass修饰的方法
 * annotation修饰的方法必须是静态的
 * @Before      在每个测试方法调用之前，这个方法都会得到调用
 * @After       每个测试方法运行结束之后，会得到运行的方法
 * @Test        需要测试的方法
 * @Ignore      忽略某些未实现的方法
 * ************************************************************
 */
public class a_CalculatorTest {

    private Calculator mCalculator;

    @BeforeClass
    public static void initial() {
        System.out.println("Calculator initial ");
    }
    @Before //在每个测试方法调用之前，这个方法都会得到调用
    public void setUp() {
        System.out.println("setUp");
        mCalculator = new Calculator();
    }

    @Test
    public void testAdd() {
        System.out.println("testAdd");
        int sum = mCalculator.add(1, 2);
        assertEquals(3,sum);
    }

    @Test
    public void multiply() {
        System.out.println("multiply");
        int sum = mCalculator.multiply(1, 2);
        assertEquals(2, sum);
    }

    @After //每个测试方法运行结束之后，会得到运行的方法
    public void remove() {
        System.out.println("remove");
        mCalculator = null;
    }

    @AfterClass
    public static void end() {
        System.out.println("Calculator end " );
    }
}
