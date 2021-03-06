package com.smart.web.controller;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.testng.annotations.BeforeClass;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;

@SpringApplicationContext({"classpath:/applicationContext.xml", "classpath:/xiaochun-servlet.xml"})
public class BaseWebTest extends UnitilsTestNG {
    
    public MockHttpServletRequest request;
    public MockHttpServletResponse response;
    public MockHttpSession session;

    @BeforeClass
    public void before() {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");

        response = new MockHttpServletResponse();
        session = new MockHttpSession();
    }
}
