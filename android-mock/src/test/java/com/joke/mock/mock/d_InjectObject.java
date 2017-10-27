package com.joke.mock.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;


/*
 * @InjectMocks 需要注入的mock对象
 * @Mock 依赖的mock对象
 * 使用这两个注解，可以直接生成相应的mock对象，直接用于测试，不需要写相应的内容
 * 但是存在一个问题，就是如果使用两个类型相同的Mock对象，那么就不去进行相应的区分注入，
 * 只使用最后的一个对象进行相应的注入，从而导致问题的产生
 * 同样的，对于构造方法来说，也无法进行相应的区分，因此如果使用两个相同参数类型的构造方法，
 * 将会产生歧义，因此需要特别注意
 * public Constructor(Object x, Object y) 如果@Mock x;@Mock y；那么将只是用只是用一个参数，
 * 只会使用最有一个@Mock y 最为注入的对象。
 * 此外需要注意的是@Mock的对象不能使用final修饰。并且不要使用Object类型
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class d_InjectObject {
    @InjectMocks
    d_MemberManager mManager;


    @Mock
    d_Member userId ;
    @Mock
    d_Member userName;

    @Test
    public void shouldDoSomething() {
        mManager.generate();
        System.out.println("Mock userId" + userId.hashCode());

        System.out.println("Mock userName" + userName.hashCode());
        verify(userName).haha();
    }
}
