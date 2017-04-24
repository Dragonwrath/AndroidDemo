package com.joke.mock.mock;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.TestClass;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//Answer的作用可以帮助我们制造一些假数据，帮助我们进行校验
public class e_AnswerTest {

    private ArrayBlockingQueue mock;
    private UserManager mUserManager;

    @Before
    public void setUp() {

    }

    //for callback;
    @Test
    public final void callbackTest() {
        ApiService service = mock(ApiService.class);
        when(service.login(any(String.class))).thenAnswer(new Answer<Callback>() {
            @Override
            public Callback answer(InvocationOnMock invocation) throws Throwable {
                Callback callback = invocation.getArgument(0);
                callback.onSucess("Success");
                return null;
            }
        });
    }

    //for dao
    @Test
    public void daoTest() {
        final List<User> userMap = new ArrayList<>();
        UserDao dao = mock(UserDao.class);
        when(dao.save(any(User.class))).thenAnswer(new Answer<User>() {
            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                User user = invocation.getArgument(0);
                userMap.add(user.getId(),user);
                return user;
            }
        });
        when(dao.find(any(Integer.class))).thenAnswer(new Answer<User>() {
            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                Integer id = invocation.getArgument(0);
                return userMap.get(id);
            }
        });
    }



}
