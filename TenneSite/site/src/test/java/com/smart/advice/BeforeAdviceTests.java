package com.smart.advice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeforeAdviceTests {

	@Test
	public void testManualBeforeAdvice() {
		NaiveWaiter waiter = new NaiveWaiter();
		
		GreetingBeforeAdvice advice = new GreetingBeforeAdvice();
		
		ProxyFactory pf = new ProxyFactory();
		
		pf.setTarget(waiter);
		
		pf.addAdvice(advice);
		
		Waiter proxy = (Waiter) pf.getProxy();
		
		proxy.greetTo("John");
		proxy.serveTo("Tom");
		
		assertTrue(true);
	}
	
	@Test
	public void testSpringBeforeAdvice() throws Exception {
		String xmlConfigPath = "com/smart/advice/beans.xml";
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(xmlConfigPath);
		
		Waiter waiter = (Waiter) ctx.getBean("waiter");
		waiter.greetTo("John");
		waiter.serveTo("Tom");
		
		ctx.close();
	}

}
