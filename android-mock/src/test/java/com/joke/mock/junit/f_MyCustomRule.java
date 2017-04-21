package com.joke.mock.junit;


import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/*
 * 自定义Rule的方法
 */
public class f_MyCustomRule  {
    @Rule
    public MyCustomRule rule = new MyCustomRule();

    @Test
    public void test() {
        int i = rule.hashCode();
        System.out.println("i = " + i);
    }

    @Test
    public void anotherTest() {
        int i = rule.hashCode();
        System.out.println("i = " + i);
    }

    class MyCustomRule implements TestRule {
        private Statement base;
        private Description description;

        @Override
        public Statement apply(Statement base, Description description) {
            this.base = base;
            this.description = description;
            return new MyStatement(base);
        }

        public class MyStatement extends Statement {
            private final Statement base;

            public MyStatement(Statement base) {
                this.base = base;
            }

            @Override
            public void evaluate() throws Throwable {
                System.out.println("MyCustomRule-->" + description.getMethodName() + " Started");
                try {
                    base.evaluate();
                } finally {
                    System.out.println("MyCustomRule-->" + description.getMethodName() + " Finished");
                }
            }
        }
    }
}
