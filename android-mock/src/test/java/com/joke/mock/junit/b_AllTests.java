package com.joke.mock.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * @RunWith 使用Suit.class可以帮助我们执行所有的测试内容
 * @Suite.SuiteClasses 可以指定测试class的文件
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        a_CalculatorTest.class
        , c_ParameterizedTestFields.class
//        , d_RuleExceptionTesterExample.class
})
public class b_AllTests {
}
