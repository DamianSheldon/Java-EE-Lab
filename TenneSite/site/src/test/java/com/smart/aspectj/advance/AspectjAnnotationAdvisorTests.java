package com.smart.aspectj.advance;

import static org.junit.Assert.*;

//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.advice.Waiter;

public class AspectjAnnotationAdvisorTests {

//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}

	@Test
	public void testNaiveWaiterGreetToAroundAspect() {
		String xmlConfigPath = "com/smart/aspectj/advance/aop-beans.xml";
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(xmlConfigPath);
		
		Waiter waiter = (Waiter) ctx.getBean("waiter");
		waiter.greetTo("John");
		
		ctx.close();
		
		assertTrue(true);
	}

}
