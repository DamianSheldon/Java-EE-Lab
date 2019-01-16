package com.smart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import com.smart.exception.UserExistException;

@Service
public class UserService {

    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    public void register(User user) throws UserExistException {
        User u = this.getUserByUserName(user.getUserName());
        if (u != null) {
            throw new UserExistException("用户名已经存在");
        }
        else {
            user.setCredit(100);
            user.setUserType(1);
            userDao.save(user);
        }
    }

    public void update(User user) {
        userDao.update(user); 
    }

    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName); 
    }

    public User getUserById(int userId) {
        return userDao.get(userId);
    }

    public void lockUser(String userName) {
        User user = userDao.getUserByUserName(userName);
        user.setLocked(User.USER_LOCK);
        userDao.update(user);
    }

    public void unlockUser(String userName) {
        User user = userDao.getUserByUserName(userName);
        user.setLocked(User.USER_UNLOCK);
    }

    public List<User> queryUserByUserName(String userName) {
        return userDao.queryUserByUserName(userName); 
    }

    public List<User> getAllUsers() {
        return userDao.loadAll();
    }

    public void loginSuccess(User user) {
        user.setCredit(5 + user.getCredit());

        LoginLog loginLog = new LoginLog();
        loginLog.setUser(user);
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(new Date());

        userDao.update(user);
        loginLogDao.save(loginLog);
    }
}
