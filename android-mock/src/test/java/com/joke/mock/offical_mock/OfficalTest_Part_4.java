package com.joke.mock.offical_mock;

import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.description;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OfficalTest_Part_4 {


    /*
     * Mockito mocks can be serialized / deserialized across classloaders (Since 1.10.0)
     * Mockito实现了从classloader中引入序列化。就好像任何对象来自于系列化之中，在mock层次中的所有
     * 类型都必须序列化，并且包含answers；
     * 还可以参考以下内容
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/MockSettings.html#serializable(org.mockito.mock.SerializableMode)
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#31
     * }
     */
    @Test
    public void step_31() {
//        // use regular serialization
//        mock(Book.class, withSettings().serializable());
//
//        // use serialization across classloaders
//        mock(Book.class, withSettings().serializable(ACROSS_CLASSLOADERS));
    }

    /*
     * Better generic support with deep stubs (Since 1.10.0)
     * 在类中如果泛型信息被引入，这就意味着类可以这样被使用而没有mock行为。
     *
     * 注意：
     *  在大多数情况下，mock返回mock对象是错误的。
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#32
     * }
     */
    @Test
    public void step_32() {
//
//        class Lines extends List<Line> {
//            // ...
//        }
//
//        lines = mock(Lines.class, RETURNS_DEEP_STUBS);
//
//        // Now Mockito understand this is not an Object but a Line
//        Line line = lines.iterator().next();
    }


    /*
     * Mockito JUnit rule (Since 1.10.17)
     * 在初始化注解中比如@Mock @Spy @InjectMocks有两种手段与方法
     * 1、使用@RunWith(MockitoJUnitRunner.class)注解JUnit测试类
     * 2、在使用@Before注解的方法中使用MockitoAnnotations.initMocks(Object)
     * Mockito.rule()
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/junit/MockitoJUnit.html#rule()
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#
     * }
     */
    @Test
    public void step_33() {
//        @RunWith(YetAnotherRunner.class)
//        public class TheTest {
//            @Rule public MockitoRule mockito = MockitoJUnit.rule();
//            // ...
//        }
    }

    /*
     * Switch on or off plugins (Since 1.10.15)
     * 一个孵化功能使它在mockito的方式，将允许切换mockito插件。
     * PluginSwitch
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/plugins/PluginSwitch.html
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#
     * }
     */
    @Test
    public void step_34() {

    }


    /*
     * Custom verification failure message (Since 2.1.0)
     * 如果校验失败的话，可以自定义的提示信息。
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#35
     * }
     */
    @Test
    public void step_35() {
        ArrayList mock = mock(ArrayList.class);
        // will print a custom message on verification failure
        verify(mock, description("This will print on failure")).clear();

        // will work with any verification mode
        verify(mock, times(2).description("someMethod should be called twice")).size();
    }

    /*
     * Java 8 Lambda Matcher Support (Since 2.1.0)
     *
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#36
     * }
     */
    @Test
    public void step_36() {
//
//        // verify a list only had strings of a certain length added to it
//        // note - this will only compile under Java 8
//        verify(list, times(2)).add(argThat(string -> string.length() < 5));
//
//        // Java 7 equivalent - not as neat
//        verify(list, times(2)).add(argThat(new ArgumentMatcher(){
//            public boolean matches(String arg) {
//                return arg.length() < 5;
//            }
//        }));
//
//        // more complex Java 8 example - where you can specify complex verification behaviour functionally
//        verify(target, times(1)).receiveComplexObject(argThat(obj -> obj.getSubObject().get(0).equals("expected")));
//
//        // this can also be used when defining the behaviour of a mock under different inputs
//        // in this case if the input list was fewer than 3 items the mock returns null
//        when(mock.someMethod(argThat(list -> list.size()<3))).willReturn(null);

    }

    /*
     *
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#37
     * }
     */
    @Test
    public void step_37() {
//        // answer by returning 12 every time
//        doAnswer(invocation -> 12).when(mock).doSomething();
//
//        // answer by using one of the parameters - converting into the right
//        // type as your go - in this case, returning the length of the second string parameter
//        // as the answer. This gets long-winded quickly, with casting of parameters.
//        doAnswer(invocation -> ((String)invocation.getArgument(1)).length())
//                .when(mock).doSomething(anyString(), anyString(), anyString());

//
//        // Example interface to be mocked has a function like:
//        void execute(String operand, Callback callback);
//
//        // the example callback has a function and the class under test
//        // will depend on the callback being invoked
//        void receive(String item);
//
//        // Java 8 - style 1
//        doAnswer(AdditionalAnswers.answerVoid((operand, callback) -> callback.receive("dummy"))
//                .when(mock).execute(anyString(), any(Callback.class));
//
//        // Java 8 - style 2 - assuming static import of AdditionalAnswers
//        doAnswer(answerVoid((String operand, Callback callback) -> callback.receive("dummy"))
//                .when(mock).execute(anyString(), any(Callback.class));
//
//        // Java 8 - style 3 - where mocking function to is a static member of test class
//        private static void dummyCallbackImpl(String operation, Callback callback) {
//            callback.receive("dummy");
//        }
//
//        doAnswer(answerVoid(TestClass::dummyCallbackImpl)
//                .when(mock).execute(anyString(), any(Callback.class));
//
//        // Java 7
//        doAnswer(answerVoid(new VoidAnswer2() {
//            public void answer(String operation, Callback callback) {
//                callback.receive("dummy");
//            }})).when(mock).execute(anyString(), any(Callback.class));
//
//        // returning a value is possible with the answer() function
//        // and the non-void version of the functional interfaces
//        // so if the mock interface had a method like
//        boolean isSameString(String input1, String input2);
//
//        // this could be mocked
//        // Java 8
//        doAnswer(AdditionalAnswers.answer((input1, input2) -> input1.equals(input2))))
//        .when(mock).execute(anyString(), anyString());
//
//        // Java 7
//        doAnswer(answer(new Answer2() {
//            public String answer(String input1, String input2) {
//                return input1 + input2;
//            }})).when(mock).execute(anyString(), anyString());

    }

    /*
     * Meta data and generic type retention (Since 2.1.0)
     * Mockito现在保留mocked方法以及类型上的注释信息，就像作为泛型属性数据一样。
     * 以前，模拟类型没有保留对类型的注释，除非它们被显式地继承，并且从未在方法上保留注释。
     * 因此，现在的条件如下：
     * 当时用Java8时，Mockito现在也会保留类型注解，这是默认行为，如果使用另一个MockMaker可能不会保留。
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#38
     * }
     */
    @Test
    public void step_38() {
//        @MyAnnotation
//        class Foo {
//            List<String> bar() { ... }
//        }
//
//        Class<?> mockType = mock(Foo.class).getClass();
//        assert mockType.isAnnotationPresent(MyAnnotation.class);
//        assert mockType.getDeclaredMethod("bar").getGenericReturnType() instanceof ParameterizedType;
    }


    /*
     * Mocking final types, enums and final methods (Since 2.1.0)
     */
    /**
     * {@see Mockito
     * https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html#39
     * }
     */
    @Test
    public void step_39() {

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
    public void step_40() {

    }


}
