package com.smart.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smart.domain.User;
import com.smart.exception.UserExistException;
import com.smart.service.UserService;

@Controller
public class RegisterController extends BaseController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, User user) {
        ModelAndView view = new ModelAndView();
        view.setViewName("/success");
        
        try {
            userService.register(user);
        }
        catch (UserExistException e) {
            view.addObject("errorMsg", "Username already exists, please choose another name.");
            view.setViewName("forward:/register.jsp");
        }
        setSessionUser(request, user);

        return view;
    }
}
