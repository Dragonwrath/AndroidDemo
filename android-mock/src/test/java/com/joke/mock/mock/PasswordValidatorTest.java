package com.joke.mock.mock;

import com.joke.mock.mock.PasswordValidator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PasswordValidatorTest {

    @Test
    public void verifyPassword() throws Exception {
        //跟创建mock类似，只不过调用的是spy方法，而不是mock方法。spy的用法
        PasswordValidator spy = Mockito.spy(PasswordValidator.class);
        //在默认情况下，spy对象会调用这个类的真实逻辑，并返回相应的返回值，这可以对照上面的真实逻辑
        spy.verifyPassword("password");
        spy.verifyPassword("test");
        //spy对象的方法也可以指定特定的行为
//        Mockito.when(spy.verifyPassword(Mockito.anyString())).thenReturn(true);
        //同样的，可以验证spy对象的方法调用情况
        spy.verifyPassword("xiaochuang_is_handsome");
        verify(spy).verifyPassword("xiaochuang_is_handsome"); //pass

    }
    @Test
    public void test() {
        PasswordValidator mock = Mockito.mock(PasswordValidator.class);
        mock.verifyPassword("haha");
        verify(mock).verifyPassword("haha");
    }


    @Test
    public void testLinkedListSpyWrong() {
        // Lets mock a LinkedList
        List<String> list = new LinkedList<>();
        list.add("0");
        List<String> spy = Mockito.spy(list);
//        spy.add("0");
        // this does not work
        // real method is called so spy.get(0)
        // throws IndexOutOfBoundsException (list is still empty)
        when(spy.get(0)).thenReturn("foo");

        assertEquals("foo", spy.get(0));

    }

}