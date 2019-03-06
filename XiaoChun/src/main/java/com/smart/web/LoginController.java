package com.smart.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smart.cons.CommonConstant;
import com.smart.domain.User;
import com.smart.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/doLogin")
    public ModelAndView login(HttpServletRequest request, User user) {
        User userInDb = userService.getUserByUserName(user.getUserName());

        ModelAndView mav = new ModelAndView();
        mav.setViewName("forward:/login.jsp");

        if (userInDb == null) {
            mav.addObject("errorMsg", "User don't exist");
        }
        else if (!userInDb.getPassword().equals(user.getPassword())) {
            mav.addObject("errorMsg", "Password isn't correct");
        }
        else if (userInDb.getLocked() == User.USER_LOCK) {
            mav.addObject("errorMsg", "User has been locked who can't login");
        }
        else {
            userInDb.setLastIp(request.getRemoteAddr());
            userInDb.setLastVisit(new Date());

            userService.loginSuccess(userInDb);

            setSessionUser(request, userInDb);

            String toUrl = (String) request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
            request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);
            if (StringUtils.isEmpty(toUrl)) {
                toUrl = "/index.html";
            }
            mav.setViewName("redirect:" + toUrl);
        }

        return mav;
    }

    @RequestMapping("/doLogout")
    public String logout(HttpSession session) {
        session.removeAttribute(CommonConstant.USER_CONTEXT);
        return "forward:/index.jsp";
    }
}
