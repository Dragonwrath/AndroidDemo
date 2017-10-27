package com.joke.mock.offical_mock;


import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyObject;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
public class OfficalTest_Part_1 {

    /*
     * 关于默认值
     * 1、 默认情况下，对于所有的方法来说，都将有一个默认的值被返回，
     * 例如对于int\Integer来说是0，对于boolean\Boolean来说是false
     * 2、虽然默认值，可以通过代码进行覆盖，但是过多的覆盖的动作，将会产生更多的代码量
     * 3、一旦被复写之后，无论调用多少次，都将返回复写之后的值
     * 4、最后，存根数据是非常重要的，尤其是当时你使用相同的参数，调用的相同的方法修改默认的值时。
     * 还有就是存根数据的顺序很重要，但是它却很少有意义，例如 当使用完全相同的方法调用时，有时候使用参数匹配器等情况时。
     */

    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#2
     * }
     */
    @Test
    public void step_2() {
        LinkedList mockedLinkList = mock(LinkedList.class);
        when(mockedLinkList.get(0)).thenReturn("first value");
        when(mockedLinkList.get(1)).thenThrow(new RuntimeException());
        //following prints "first"
        System.out.println(mockedLinkList.get(0));
        //following throws runtime exception
//        System.out.println(mockedLinkList.get(1));

        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedLinkList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        //If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
        verify(mockedLinkList).get(0);
//        verify(mockedLinkList).get(1);
    }


    /*
     * Mockito可以使用anyX()来替代任何的参数，但是如果需要自定义参数，那么就需要使用到 argument matchers
     * 使用ArgumentMatcher.argThat(matcher)方法，可以帮助我们校验相应的参数是否满足条件，从而验证有效性
     * 实际上所有的any方法，都是实现了相应的Matcher
     * 需要注意的是，如果你在调用相应的方法参数的时候使用了any方法，也就是说使用了Matcher，那么所有的方法都必须
     * 使用argument matchers
     *    verify(mock).someMethod(anyInt(), anyString(), eq("third argument"));
     *    //above is correct - eq() is also an argument matcher
     *    verify(mock).someMethod(anyInt(), anyString(), "third argument");
     *    //above is incorrect -
     *     exception will be thrown because third argument is given without an argument matcher.
     */
    /**
     * {@see Mockito
     *  https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#3
     * }
     * {@see AdditionalMatchers
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/AdditionalMatchers.html
     * }
     * [@see ArgumentMatchers
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/ArgumentMatchers.html
     * }
     * {@see MockitoHamcrest
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/hamcrest/MockitoHamcrest.html
     * }
     */
    @Test
    public void step_3() {
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        //stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
         when(mockedList.contains(argThat(isValid()))).thenReturn(true);

        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using an argument matcher
        verify(mockedList).get(anyInt());

        mockedList.add("element");
        //argument matchers can also be written as Java 8 Lambdas
//        verify(mockedList).add(argThat(someString -> someString.length() > 5));
        verify(mockedList).add(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String argument) {
                return argument.length() > 5;
            }
        }));

        anyObject();
    }

    private ArgumentMatcher isValid() {
        return  new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String argument) {
                return argument.equals("element");
            }
        };
    }

    /*
     * 默认情况下，time(1)是默认被使用的，相应的方法是隐藏的，因此无需调用
     * 如果使用atLeast方法，可以校验至少执行了多少次
     * 如果使用atMost 方法，可以校验至多运行了多少次
     * never()代表，依次都没有运行
     */
    /**
     * {@see Verifying exact number of invocations / at least x / never
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#4
     * }
     */
    @Test
    public void step_4() {

        LinkedList<Object> mockedList = mock(LinkedList.class);

        //using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("five times");
        verify(mockedList, atMost(5)).add("three times");
    }

    /*
     * 可以通过doThrow方法抛出相应的异常。
     * 下面的语句就表示 党执行mockedList执行clear()方法的时候，将抛出一个异常
     */
    /**
     * {@see Stubbing void methods with exceptions
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#5
     * }
     */
    @Test
    public void step_5(){
        LinkedList mockedList = mock(LinkedList.class);
        doThrow(new RuntimeException()).when(mockedList).clear();
        //following throws RuntimeException:
        mockedList.clear();
    }

    /*
     * 使用inOrder可以帮助你来校验是否是顺序执行了需要测试的内容
     * 同时，你也可以通过InOrder对象，只传递与按顺序验证相关的mocks
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#6
     * }
     */
    @Test
    public void step_6() {
        // A. Single mock whose methods must be invoked in a particular order
        List<String> singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        //following will make sure that add is first called with "was added first, then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List<String> firstMock = mock(List.class);
        List<String> secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        InOrder anotherOrder = inOrder(secondMock,firstMock);

        //following will make sure that firstMock was called before secondMock
        anotherOrder.verify(firstMock).add("was called first");
        anotherOrder.verify(secondMock).add("was called second");

        // Oh, and A + B can be mixed together at will
    }

    /*
     * Making sure interaction(s) never happened on mock
     * 确认相互的测试不会产生影响
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#7
     * }
     */
    @Test
    public void step_7() {

        LinkedList<String> mockOne = mock(LinkedList.class);
        LinkedList<String> mockTwo = mock(LinkedList.class);
        LinkedList<String> mockThree = mock(LinkedList.class);

        //using mocks - only mockOne is interacted
        mockOne.add("one");

        //ordinary verification
        verify(mockOne).add("one");

        //verify that method was never called on a mock
        verify(mockOne, never()).add("two");

        //verify that other mocks were not interacted
        verifyZeroInteractions(mockTwo, mockThree);

    }

    /*
     * Finding redundant invocations
     * 通过使用verifyNoMoreInteractions确保相应的调用只执行一次
     * 同时不建议在每个测试方法中使用verifyNoMoreInteractions（）。
     * 如果调用never()方法，则会更加明确的表明相应的意图
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#8
     * }
     */
    @Test
    public void step_8() {
        LinkedList<String> mockedList = mock(LinkedList.class);

        //using mocks
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");
        verify(mockedList).add("two");

        //following verification will fail
        verifyNoMoreInteractions(mockedList);

    }

    /*
     * Shorthand for mocks creation - @Mock annotation
     * 使用@Mock可以帮助我们更加快速的创建相应的mock对象，同时拥有更好的可读性
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#9
     * }
     * {@see MockitoRule
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/junit/MockitoRule.html
     * }
     * {@see MockitoAnnotations
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/MockitoAnnotations.html
     * }
     */
    @Test
    public void step_9() {
//        public class ArticleManagerTest {
//
//            @Mock
//            private ArticleCalculator calculator;
//            @Mock
//            private ArticleDatabase database;
//            @Mock
//            private UserProvider userProvider;
//
//            private ArticleManager manager;
//        }
//        Important! This needs to be somewhere in the base class or a test runner:
//
//        MockitoAnnotations.initMocks(testClass);
    }


    /*
     *  Stubbing consecutive calls (iterator-style stubbing)
     *  给多次调用同样的方法，添加不同的返回值
     *  需要注意的是如果多次使用同样参数的方法，之后调用thenReturn，将会产生的效果是
     *  后一个会覆盖前一个的返回值。
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#10
     * }
     */
    @Test
    public void step_10() {
//
//        when(mock.someMethod("some arg"))
//                .thenThrow(new RuntimeException())
//                .thenReturn("foo");
//
//        //First call: throws runtime exception:
//        mock.someMethod("some arg");
//
//        //Second call: prints "foo"
//        System.out.println(mock.someMethod("some arg"));
//
//        //Any consecutive call: prints "foo" as well (last stubbing wins).
//        System.out.println(mock.someMethod("some arg"));
//


//        Alternative, shorter version of consecutive stubbing:
//
//        when(mock.someMethod("some arg"))
//                .thenReturn("one", "two", "three");
//
//        Warning : if instead of chaining .thenReturn() calls, multiple stubbing with the same matchers or arguments is used, then each stubbing will override the previous one:
//


//        //TODO:All mock.someMethod("some arg") calls will return "two"
//        when(mock.someMethod("some arg"))
//                .thenReturn("one")
//        when(mock.someMethod("some arg"))
//                .thenReturn("two")

    }

}
