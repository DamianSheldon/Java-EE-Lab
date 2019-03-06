package com.smart.aspectj.advance;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.advice.NaiveWaiter;
import com.smart.advice.Waiter;

public class AspectjAnnotationAdvisorTests {

	String xmlConfigPath = "com/smart/aspectj/advance/aop-beans.xml";
	ClassPathXmlApplicationContext ctx;
	
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext(xmlConfigPath);
	}

	@After
	public void tearDown() throws Exception {
		ctx.close();
	}

	@Test
	public void testNaiveWaiterGreetToAroundAspect() {
		Waiter waiter = (Waiter) ctx.getBean("waiter");
		waiter.greetTo("John");
		
		assertTrue(true);
	}
	
	@Test
	public void testBindJoinPointParamsAspect() {
		NaiveWaiter waiter = (NaiveWaiter) ctx.getBean("waiter");
		waiter.smile("John", 2);
		
		assertTrue(true);
	}
	
}
