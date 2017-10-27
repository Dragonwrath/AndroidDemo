package com.joke.mock.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/*
 * @Rule 给测试的内容增加一些规则，或者一些相应的条件，
 *  例如我们使用Rule规则下的ExpectedException 可以帮助我们定义相应的异常
  *  throwsIllegalArgumentExceptionIfIconIsNull 中可以利用exception，来进行一场类型的检查
 *  以及异常信息的检查
 */
public class d_RuleExceptionTesterExample {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throwsIllegalArgumentExceptionIfIconIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Negative value not allowed");
        throw new IllegalArgumentException("Negative value not allowed");
//        ClassToBeTested t = new ClassToBeTested();
//        t.methodToBeTest(-1);
    }

    class ClassToBeTested {
        void methodToBeTest(int i) {
            if (i < 0)
                throw new IllegalArgumentException("Negative value not allowed");
        }
    }
}
