package com.joke.mock.offical_mock;


import org.junit.Test;

import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

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
     * Automatic instantiation of @Spies, @InjectMocks and constructor injection goodness (Since 1.9.0)
     * 我们通过组合@Spies，将会实例化、注入在使用@InjectMocks的构造函数、setter方法、或者全局变量上
     *
     * {@see
     * 了解相应的方法调用
     *  https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/MockitoAnnotations.html#initMocks(java.lang.Object)
     *  了解JUnitRunner规则
     *  https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/junit/MockitoJUnitRunner.html
     *  了解MockitoRule
     *  https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/junit/MockitoRule.html
     * }
     * {@see InjectMocks 可以了解更多的注入的规则，以及限制
     *  https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/InjectMocks.html
     * }
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#23
     * }
     */
    @Test
    public void step_23() {

    }

    /*
     * One-liner stubs (Since 1.9.0)
     * 我们可以可用一行代码来创建相应的mock对象保证简洁性，例如下面的例子
     *  public class CarTest {
     *   Car boringStubbedCar = when(mock(Car.class).shiftGear()).thenThrow(EngineNotStarted.class).getMock();
     *      @Test public void should... {}
     *  }
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#24
     * }
     */
    @Test
    public void step_24() {
        ArrayList test = when(mock(ArrayList.class).add("Test")).thenThrow(new RuntimeException()).getMock();
        test.add("haha"); //正常运行
        test.add("Test"); //抛出异常

    }

    /*
     * Verification ignoring stubs (Since 1.9.0)
     *  verifyNoMoreInteractions() 或者verification inOrder() 可以帮助我们减少相应的冗余验证
     * 警告：
     *  需要注意的是ignoreStubs() 将会导致过度的使用verifyNoMoreInteractions(ignoreStubs(...))方法
     *  我们并不应该在每次test测试的时候都调用该verify方法，因为它只是在测试工具中作为一个简单的、方便的断言
     *  应当在那些需要使用该方法的时候再使用。
     *  {@see
     *  https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#verifyNoMoreInteractions(java.lang.Object...)
     *  https://monkeyisland.pl/2008/07/12/should-i-worry-about-the-unexpected/
     *  }
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#
     * }
     */
    @Test
    public void step_25() {
        ArrayList mock = mock(ArrayList.class);
        ArrayList mockTwo = mock(ArrayList.class);

        mock.clear();
        mockTwo.clear();
        verify(mock).clear();
        verify(mockTwo).clear();

        //ignores all stubbed methods:
        verifyNoMoreInteractions(ignoreStubs(mock, mockTwo));

        //creates InOrder that will ignore stubbed
        InOrder inOrder = inOrder(ignoreStubs(mock, mockTwo));
        inOrder.verify(mock).clear();
        inOrder.verify(mockTwo).clear();
        inOrder.verifyNoMoreInteractions();
    }

    /*
     * Mocking details (Improved in 2.2.x)
     * 可以帮助我们获取mock对象或者spy对象的一些具体的细节
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/MockingDetails.html
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#26
     * }
     */
    @Test
    public void step_26() {

//        //To identify whether a particular object is a mock or a spy:
//        Mockito.mockingDetails(someObject).isMock();
//        Mockito.mockingDetails(someObject).isSpy();

//        //Getting details like type to mock or default answer:
//        MockingDetails details = mockingDetails(mock);
//        details.getMockCreationSettings().getTypeToMock();
//        details.getMockCreationSettings().getDefaultAnswer();

//        //Getting interactions and stubbings of the mock:
//        MockingDetails details = mockingDetails(mock);
//        details.getInteractions();
//        details.getStubbings();

//        //Printing all interactions (including stubbing, unused stubs)
//        System.out.println(mockingDetails(mock).printInvocations());
    }

    /*
     * Delegate calls to real instance (Since 1.9.5)
     * 利用spies或者部分模拟的对象很难使用一般的spyApi来进行mock或者spy。而从1.10.11开始，这种委托的方法
     * 可能与mock相同，也可能与之不相同。如果类型不相同，那么在代表的类型中找到一个匹配的方法，否则将抛出异常
     * 以下是可能用到这种功能的情形：
     *  Final classes but with an interface
     *  Already custom proxied object
     *  Special objects with a finalize method, i.e. to avoid executing it 2 times
     *  与通常的spy对象的不同之处在于
     *  1、通常的spy对象((spy(Object))从spied的实例中包含了所有的状态，并且这些方法都可以在spy对象上引用。
     *  这个spied的实例只是被当做，从已经创建的mock对象上复制所有的状态的一个对象。如果你在一个通常的spy对象
     *  上调用一些方法，并且它会在这个spy对象向调用其他方法。因此，这些调用将会被记录下来用于验证，并且他们可
     *  以有效的修改相应的存根数据(stubbed)
     *  2、mock对象代表着一个可以接收所有方法的简单委托的对象。这个委托可以在任何时候上调用它的所有方法。
     *  如果你在一个mock对象上调用一个方法，并且它内部在这个mock对象上调用了其他的方法，那么这些调用将不会
     *  记录下来用以验证，存根数据(stubbing)也没有对他们有任何影响。Mock相对于一个通常的spy对象来说，没有
     *  那么的强大，但是它可以非常有用的，在那些spy对象不能被创建的时候
     *
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#27
     * }
     */
    @Test
    public void step_27() {

    }

    /*
     * MockMaker API (Since 1.9.5)
     * 由于谷歌安卓上针对于设备以及补丁的需求，Mockito现在提供了一个扩展点，去替代代理生成引擎
     * 默认情况下，Mockito使用 byte-buddy来创建动态的代理
     * {byte-buddy
     *  https://github.com/raphw/byte-buddy
     * }
     * 这个扩展点是针对于那些需要扩展Mockito的高级用户。现在可以利用dexMatcher来帮助进行一些
     * Android方面的Mockito测试
     * 更多的内容可以参考MockMarker
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/plugins/MockMaker.html
     *
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#28
     * }
     */
    @Test
    public void step_28() {

    }

    /*
     * BDD style verification (Since 1.10.0)
     * Enables Behavior Driven Development (BDD) style verification by
     * starting verification with the BDD then keyword.
     * 可以参考以下的连接，获取更多的内容
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/BDDMockito.html#then(T)
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#29
     * }
     */
    @Test
    public void step_29() {

    }

    /*
     * Spying or mocking abstract classes (Since 1.10.12, further enhanced in 2.7.13 and 2.7.14)
     * 在模拟抽象类的时候，这个功能非常的有用，因为它可以让你不再需要提供一个抽象类的实例
     * 目前只有较少参数的的改造方法的抽象类可以支持
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#30
     * }
     */
    @Test
    public void step_30() {
//        //convenience API, new overloaded spy() method:
//        SomeAbstract spy = spy(SomeAbstract.class);
//
//        //Mocking abstract methods, spying default methods of an interface (only avilable since 2.7.13)
//        Function function = spy(Function.class);
//
//        //Robust API, via settings builder:
//        OtherAbstract spy = mock(OtherAbstract.class, withSettings()
//                .useConstructor().defaultAnswer(CALLS_REAL_METHODS));
//
//        //Mocking an abstract class with constructor arguments (only available since 2.7.14)
//        SomeAbstract spy = mock(SomeAbstract.class, withSettings()
//                .useConstructor("arg1", 123).defaultAnswer(CALLS_REAL_METHODS));
//
//        //Mocking a non-static inner abstract class:
//        InnerAbstract spy = mock(InnerAbstract.class, withSettings()
//                .useConstructor().outerInstance(outerInstance).defaultAnswer(CALLS_REAL_METHODS));

    }

}
