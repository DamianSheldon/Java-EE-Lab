package com.smart.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;

import com.smart.domain.User;
import com.smart.test.dataset.util.XlsDataSetBeanFactory;

public class UserDaoTests extends BaseDaoTest {

    @SpringBean("userDao")
    private UserDao userDao;

    @Test
    @DataSet("XiaoChun.Users.xls")
    public void findUserByUserName() throws Exception {
        User user = userDao.getUserByUserName("tony");
        assertNull("不存在用户名为tony的用户！", user);

        user = userDao.getUserByUserName("jan");
        assertNotNull("Jan用户存在！", user);
        assertEquals("jan", user.getUserName());
        assertEquals("123456", user.getPassword());
        assertEquals(10, user.getCredit());
    }

    @Test
    @DataSet("XiaoChun.SaveUser.xls")
    @ExpectedDataSet("XiaoChun.ExpectedSaveUser.xls")
    public void saveUser() throws Exception {
        User u = XlsDataSetBeanFactory.createBean(UserDaoTests.class, "XiaoChun.SaveUser.xls", "t_user", User.class);
        userDao.update(u);
    }

}
