package com.smart.aspectj.example;

import static org.junit.Assert.*;

//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import com.smart.advice.NaiveWaiter;
import com.smart.advice.Waiter;

public class AspectJProxyTests {

//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}

	@Test
	public void testAspectJProxy() {
		NaiveWaiter waiter = new NaiveWaiter();
		
		AspectJProxyFactory factory = new AspectJProxyFactory();
		
		factory.setTarget(waiter);
		
		factory.addAspect(PreGreetingAspect.class);
		
		Waiter waiterProxy = factory.getProxy();
		
		waiterProxy.greetTo("John");
		waiterProxy.serveTo("John");
		
		assertTrue(true);
	}

}
