package com.joke.mock.offical_mock;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;



public class OfficalTest_Part_2 {

    /*
     * Stubbing with callbacks
     * 可以使用Answer来进行修改相应的,比如在下面的例子中,我们修改相应的返回值,
     * 调用相应的add方法时，就会返回我们自定义的结果
     * 最好 thenReturn() or thenThrow()等方法，来进行相应的测试。
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#11
     * }
     */
    @Test
    public void step_11() {
        final LinkedList mockedLinkList = mock(LinkedList.class);
        when(mockedLinkList.remove(anyInt())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                Object mock = invocation.getMock();
                return "called with arguments: " + Arrays.toString(arguments);

            }
        });
        System.out.println(mockedLinkList.remove(999));

    }

    /*
     * doReturn()|doThrow()| doAnswer()|doNothing()|doCallRealMethod() family of methods
     *
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#12
     * }
     */
    @Test
    public void step_12() {
        final LinkedList mockedLinkList = mock(LinkedList.class);

        when(mockedLinkList.get(0)).thenReturn(false);
        doAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                Object mock = invocation.getMock();

                return mock == mockedLinkList;
            }
        }).when(mockedLinkList).get(anyInt());

        System.out.println(mockedLinkList.get(0));


    }

    /*
     * 当使用spy的时候，会真正调用相应的方法(除非使用其他手段修改相应的返回值， 比如doXxx())
     * 注意：应当尽可能仔细使用，并且尽量少地使用spy，比如在处理遗留的代码的时候
     * 在一个真正的对象上使用Spy，可以理解为“partial mocking”；但是在1.8之前，Mockito并不是真正
     * partial mock，原因在于，我们认为partial mock是代码的一部分。在某些方面，我们发现了使用部分mocks
     * 的合理之处(例如：第三方接口，遗留代码的临时重构，全部的文章，参考以下内容
     * {@see  partial-mocking
     * https://monkeyisland.pl/2009/01/13/subclass-and-override-vs-partial-mocking-vs-refactoring/})
     * 有一点需要特别注意的是，在使用 when(Object)方法调用spy对象的时候，建议使用以下的方法替代doReturn|Answer|Throw()
     * 例如
     *    //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
     *       when(spy.get(0)).thenReturn("ArrayList");
     *   由于spy并没有初始化，因此将会抛出相应的异常
     *    //You have to use doReturn() for stubbing
     *    doReturn("ArrayList").when(spy).get(0);
     *
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#13
     * }
     */
    @Test
    public void step_13() {

        List list = new LinkedList();
        List<String>  spy = spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        when(spy.get(0)).thenReturn("ArrayList");

        //You have to use doReturn() for stubbing
        doReturn("ArrayList").when(spy).get(0);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
    }


    /*
     * Changing default return values of unstubbed invocations (Since 1.7)
     * 我们可以给相应的返回值，指定一个默认的规则。
     * 这是想当高级的功能，并且对于旧系统，来说是非常有帮助的
     * 默认情况下，你根本不需要调用它
     * 更多的内容，可以参考默认的实现。
     * {@see  RETURNS_SMART_NULLS
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#RETURNS_SMART_NULLS
     * }
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#14
     * }
     */
    @Test
    public void step_14() {

        List mock = mock(ArrayList.class, Mockito.RETURNS_SMART_NULLS);
        List mockTwo = mock(List.class, new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return false;
            }
        });
        System.out.println( mockTwo.add("haha"));
    }



    /*
     * Capturing arguments for further assertions (Since 1.8.0)
     * 这个功能，可以帮助你检验相应的参数的正确性
     * 警告：必须记住的是使用ArgumentCaptor 功能应该是检验而不是用来替代默认值。
     * 如果使用ArgumentCaptor 将减少代码的可读性，因为捕获是在assert之外创建，
     * （例如在aka verify 或者‘then’）
     * 它同时减少了定位缺陷，因为如果stubbed方法没有被调用，那么将不会有参数被捕获
     * ArgumentCaptor 更适用于以下情形：
     * 1、自定义argument matcher不太可能被重用
     * 2、你只需要验证函数参数的只就可以完成校验
     * {@see ArgumentMatcher
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/ArgumentMatcher.html
     * }
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#15
     * }
     */
    @Test
    public void step_15() {
        ArrayList mock = mock(ArrayList.class);
        mock.add("John");
        ArgumentCaptor<List> argument = ArgumentCaptor.forClass(ArrayList.class);
        verify(mock).add(argument.capture());
        Assert.assertEquals("John", argument.getValue());
    }

    /*
     * Real partial mocks (Since 1.8.0)
     * 在部分情况下partial mocks是非常有用的
     * spy()与spy(Object)方法并没有产生正常的partial mocks，具体信息请阅读
     * {@see
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#13
     * }
     * 需要注意的是：
     * 最好在测试第三方接口，以及遗留代码的时候使用spy
     * 不要在新设计的代码、可以测试的代码，以及良好设计的代码上使用spy
     *
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#16
     * }
     */
    @Test
    public void step_16() {
        //you can create partial mock with spy() method:
        List list = spy(new LinkedList());

        //you can enable partial mock capabilities selectively on mocks:
        ArrayList mock = mock(ArrayList.class);
        //Be sure the real implementation is 'safe'.
        //If real implementation throws exceptions or depends on specific state of the object then you're in trouble.
        when(mock.add("haha")).thenCallRealMethod();

    }

    /*
     *  Resetting mocks (Since 1.8.0)
     *  "Please keep us small & focused on single behavior".
     *  是我们应当遵循的规则，因此我们通常并不需要使用reset，除非在一个冗长的、超量的测试中使用。
     *  官方添加rest方法的唯一原因就是可以使用容器注入的模拟器。
     *  {@see FAQ
     *  https://github.com/mockito/mockito/wiki/FAQ
     *  }
     *  告诉你自己：
     *      如果是使用reset方法，那么你可能写得太多了
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#17
     * }
     */
    @Test
    public void step_17() {
        List mock = mock(List.class);
        when(mock.size()).thenReturn(10);
        mock.add(1);

        reset(mock);
        //at this point the mock forgot any interactions & stubbing

    }

    /*
     * Troubleshooting & validating framework usage
     *  {@see FAQ
     *  https://github.com/mockito/mockito/wiki/FAQ
     *  }
     *  或者可以参考
     *  http://groups.google.com/group/mockito
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#18
     * }
     */
    @Test
    public void step_18() {

    }


    /*
     * Aliases for behavior driven development (Since 1.8.0)
     * Behavior Driven Development (基于行为驱动的开发)在执行测试时，可以使用
     *  //given //when //then等方法作为你测试方法的一部分。
     * {@see Behavior Driven Development (BDD)
     *  http://en.wikipedia.org/wiki/Behavior_Driven_Development
     * }
     * 问题是当前stubbing api与when字的规范作用，没有很好地集成//given//when//then注释。
     * 这是因为它属于given测试内容的stubbing，并不属于when测试内容的sutbbing
     * 因此，因此，BDDMockito类引入一个别名，
     * 以便使用BDDMockito.given（Object）方法进行stub方法调用。
     * 现在它真的很好地与BDD样式测试的给定组件集成！
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#19
     * }
     */
    @Test
    public void step_19() {
        ArrayList mock = mock(ArrayList.class);
        BDDMockito.given(mock.add("Test")).willReturn(false);
        System.out.println("mock.add(\"Test\") = " + mock.add("Test"));
        verify(mock).add("Test");
    }

    /*
     * Serializable mocks (Since 1.8.1)
     * Mocks可以被创作为可序列化对象。有了这个功能，你可以在一个需要依赖的地方使用一个模拟来进行序列化。
     * 警告：这个功能应该在单元测试中很少用到。
     * 对于具有不可靠的外部依赖性的BDD规范的特定用例，实施了该行为。
     * 这是在一个Web环境中，来自外部依赖关系的对象被序列化以在层之间传递。
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#
     * }
     */
    @Test
    public void step_20() {
        List serializableMock = mock(List.class, withSettings().serializable());
        //创建一个真正的对象spy serializable 是一个需要更多的努力，
        //因为spy(object)方法没有接受MockSettings的重载版本，别担心，几乎不会使用它。
        List<Object> list = new ArrayList<Object>();
        List<Object> spy = mock(ArrayList.class, withSettings()
                .spiedInstance(list)
                .defaultAnswer(CALLS_REAL_METHODS)
                .serializable());

    }


}
