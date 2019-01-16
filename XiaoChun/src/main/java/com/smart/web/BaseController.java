package com.smart.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.smart.cons.CommonConstant;
import com.smart.domain.User;

public class BaseController {
    protected static final String ERROR_MSG_KEY = "errorMsg";

    protected User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
    }

    protected void setSessionUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(CommonConstant.USER_CONTEXT, user);
    }
    
    public final String getAppbaseUrl(HttpServletRequest request, String url) {
        Assert.hasLength(url, "Url can't be empty");
        Assert.isTrue(url.startsWith("/"), "Must prefix with /");

        return request.getContextPath() + url;
    }
}
