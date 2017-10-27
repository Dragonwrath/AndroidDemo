package com.joke.mock.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/*
* Mockito.anyXxx() 表示任意的参数类型可能为null，其中有基本数据类型，以及集合类型
*
* Mockito.verify(mockObject,Mockito.time(x))表示测试的次数
*
* Mockito.mock(Xxx.class)创建一个mock对象
* 一个mock对象的所有非void方法都将返回默认值：int、long类型方法将返回0，boolean方法将返回false，对象方法将返回null
*/
public class LoginPresenterTest {

    private UserManager mMockUserManager;
    private LoginPresenter mLoginPresenter;
    private PasswordValidator mPasswordValidator;

    @Before
    public void setUp() {
        //创建一个虚拟的mock对象
        mMockUserManager = Mockito.mock(UserManager.class);
        //创建相应的对象引用相应的方法
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.setUserManager(mMockUserManager);

        //初始化相应的对象
        mPasswordValidator = Mockito.mock(PasswordValidator.class);
        mLoginPresenter.setPasswordValidator(mPasswordValidator);

    }

    //step 1
    //basic mock object  验证方法调用
    @Test
    public void testLogin() {
        //调用相应的方法
        mLoginPresenter.login("xiaochuang", "xiaochuang password");
        //用来验证相应的类有没有执行相应的方法，也就是mock对象是不是,是不是使用同样的参数执行了同样的方法
        Mockito.verify(mMockUserManager).performLogin("xiaochuang", "xiaochuang password");
    }

    //step_2
    //指定mock对象的某些方法的行为
    //需要测试的方法是singIn，因此我们不必要关心相应的mPasswordValidator的相应的方法细节，
    //只需要指定特定的值即可

    @Test
    public void testSignInWhileFalse() {
        mLoginPresenter.singIn("xiaochuang", "xiaochuang password");
        Mockito.when(mPasswordValidator.verifyPassword(Mockito.anyString())).thenReturn(false);
        Mockito.verify(mMockUserManager).singIn(Mockito.anyString(), Mockito.anyString());
    }

    //step_3
    //由于我们测试的是相应的方法的结果，我们并不关心相应的网络的细节，
    // 只是验证方法的完整性以及正确性
    @Test
    public void testValidate() {
        mLoginPresenter.validate("xiaochuang", "xiaochuang password");

        Mockito.verify(mMockUserManager).validate(Mockito.anyString(), Mockito.anyString(), Mockito.any(NetworkCallback.class));
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                NetworkCallback callback = (NetworkCallback) arguments[2];
                callback.onFailure(500,"Server error");
                callback.onSuccess("Success");
                return callback;
            }
        }).when(mMockUserManager).validate(Mockito.anyString(), Mockito.anyString(), Mockito.any(NetworkCallback.class));
    }

}