package com.smart.web;

import static com.smart.cons.CommonConstant.LOGIN_TO_URL;
import static com.smart.cons.CommonConstant.USER_CONTEXT;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.smart.domain.User;

public class ForumFilter implements Filter {
    private static final String FILTERED_REQUEST = "@@session_context_filtered_request";

    private static final String[] INHERENT_ESCAPE_URIS = { "/index.jsp", "/index.html", "/login.jsp", 
        "/login/doLogin.html", "/register.jsp", "/register.html", "/board/listBoardTopics-", "/board/listTopicPosts-" };

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
            chain.doFilter(request, response);
        }
        else {
            request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            User userContext = getSessionUser(httpRequest);

            if (userContext == null && !isURILogin(httpRequest.getRequestURI(), httpRequest)) {
                String toUrl = httpRequest.getRequestURL().toString();

                if (!StringUtils.isEmpty(httpRequest.getQueryString())) {
                    toUrl += "?" + httpRequest.getQueryString();
                }

                httpRequest.getSession().setAttribute(LOGIN_TO_URL, toUrl);

                request.getRequestDispatcher("/login.jsp").forward(request, response);

                return;
            }
            
            chain.doFilter(request, response);
        }
	}

	public void destroy() {

	}

    private boolean isURILogin(String requestURI, HttpServletRequest request) {
        if (request.getContextPath().equalsIgnoreCase(requestURI) 
                || (request.getContextPath() + "/").equalsIgnoreCase(requestURI)) {
            return true; 
        }

        for (String uri : INHERENT_ESCAPE_URIS) {
            if (requestURI != null && requestURI.indexOf(uri) >= 0) {
                return true;
            }
        }

        return false;
    }

    protected User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(USER_CONTEXT);
    }

}
