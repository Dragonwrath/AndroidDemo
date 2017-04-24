package com.joke.mock.offical_mock;


import org.junit.Test;

public class OfficalTest_Part_3 {

    /*
     * New annotations: @Captor, @Spy, @InjectMocks (Since 1.8.3)
     * @Captor simplifies creation of ArgumentCaptor - useful when the argument to capture is a nasty generic class and you want to avoid compiler warnings
     * @Spy - you can use it instead spy(Object).
     * @InjectMocks - injects mock or spy fields into tested object automatically.
     * 需要注意的是InjectMocks可以与@Spy进行组合使用。这就意味着，Mockito将会注入mocks到 partial mock
     * 用以进行测试。虽然更加的复杂，但是却又一个很好的原因可以让你使用它，那就是它可以作为你使用partial mock
     * 的最后的手段。
     * 这些所有的注解，只在 MockitoAnnotations.initMocks(Object)中处理，就像你使用@Mock注解时，你可以使用
     * 内置的MockitoJUnitRunner or rule: MockitoRule.
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#21
     * }
     */
    @Test
    public void step_21() {

    }

    /*
     * Verification with timeout (Since 1.8.5)
     * 允许校验超时，它将会导致等待一个特殊的时间段，从而期望的事情发生，而不是立即发生。
     * 在测试并发的时候，它将可能很有用。
     * 它应该很少被使用到-在找到一个更好的测试多线程系统的方法。
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#22
     * }
     */
    @Test
    public void step_22() {
//
//        //passes when someMethod() is called within given time span
//        verify(mock, timeout(100)).someMethod();
//        //above is an alias to:
//        verify(mock, timeout(100).times(1)).someMethod();
//
//        //passes when someMethod() is called *exactly* 2 times within given time span
//        verify(mock, timeout(100).times(2)).someMethod();
//
//        //passes when someMethod() is called *at least* 2 times within given time span
//        verify(mock, timeout(100).atLeast(2)).someMethod();
//
//        //verifies someMethod() within given time span using given verification mode
//        //useful only if you have your own custom verification modes.
//        verify(mock, new Timeout(100, yourOwnVerificationMode)).someMethod();

    }


    /*
     *
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#
     * }
     */
    @Test
    public void step_() {

    }


}
