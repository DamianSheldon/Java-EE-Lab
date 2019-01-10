package com.smart.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.smart.dao.UserDao;
import com.smart.domain.User;
import com.smart.exception.UserExistException;

public class UserServiceTests extends BaseServiceTest {
    private UserDao userDao;
    private UserService userService;

    @BeforeClass
    public void init() {
        userDao = mock(UserDao.class);
        userService = new UserService();
        ReflectionTestUtils.setField(userService, "userDao", userDao);
    }

    @Test
    public void testRegister() throws UserExistException {
        User user = new User();
        user.setUserName("testwww");
        user.setPassword("1234");

        doAnswer(new Answer<User>() {
            public User answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                User user = (User) args[0];
                if (user != null) {
                    user.setUserId(1);
                }
                return user;
            }
        }).when(userDao).save(user);

        userService.register(user);
        assertEquals(user.getUserId(), 1);

        verify(userDao, times(1)).save(user);
    }

    @Test
    public void testGetUserByUserName() {
       User user = new User();
       user.setUserName("tom");
       user.setPassword("1234");
       user.setCredit(100);
       doReturn(user).when(userDao).getUserByUserName("tom");

       User u = userService.getUserByUserName("tom");
       assertNotNull(u);
       assertEquals(u.getUserName(), user.getUserName());
       verify(userDao, times(1)).getUserByUserName("tom");
    }

    @Test
    public void lockUser() {
        User user = new User();
        user.setUserName("tom");
        user.setPassword("1234");
        doReturn(user).when(userDao).getUserByUserName("tom");
        doNothing().when(userDao).update(user);

        userService.lockUser("tom");
        User u = userService.getUserByUserName("tom");

        assertEquals(User.USER_LOCK, u.getLocked());
    }

    @Test
    public void unlockUser() {
        User user = new User();
        user.setUserName("tom");
        user.setPassword("1234");
        user.setLocked(User.USER_LOCK);
        doReturn(user).when(userDao).getUserByUserName("tom");
        doNothing().when(userDao).update(user);

        userService.unlockUser("tom");
        User u = userService.getUserByUserName("tom");
        assertEquals(User.USER_UNLOCK, u.getLocked());
    }
}
