package com.smart.advice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

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

}
