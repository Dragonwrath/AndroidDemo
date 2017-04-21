package com.joke.mock.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


/*
 * @RunWith可以用来辅助输入一些参数的处理,作为他们的参数输入
 * @Parameterized.Parameter 可以指定具体的数字，分别代表第一个参数、第二个参数等等
 * @Parameterized.Parameters 用来获取相应的初始化的数值，以及期望数值，我们可以看下面的例子
 */
@RunWith(Parameterized.class)
public class c_ParameterizedTestFields {
    // fields used together with @Parameter must be public
    @Parameterized.Parameter()
    public int m1;
    @Parameterized.Parameter(1)
    public int m2;
    @Parameterized.Parameter(2)
    public int result;
    @Parameterized.Parameter(3)
    public int plus;

//    // creates the test data
//    @Parameterized.Parameters
//    public static Collection<Object[]> data() {
//        Object[][] data = new Object[][] { { 1 , 2, 2 }, { 5, 3, 15 }, { 121, 4, 484 } };
//        return Arrays.asList(data);
//    }

    @Parameterized.Parameters
    public static Collection<Integer[]> test() {
        ArrayList<Integer[]> integers = new ArrayList<>();
        integers.add(new Integer[]{1,2,2,1});
        return integers;
    }

    @Test
    public void testMultiplyException() {
        MyClass tester = new MyClass();
        assertEquals("Result", result*plus, tester.multiply(m1, m2));
    }

    @Test
    public void testPlusExecption() {
        MyClass tester = new MyClass();
        assertEquals("Result", result + plus, tester.plus(m1, m2));
    }

    // class to be tested
    class MyClass {
        public int multiply(int i, int j) {
            return i *j;
        }
        public int plus(int i, int j) {
            return i + j;
        }
    }
}
