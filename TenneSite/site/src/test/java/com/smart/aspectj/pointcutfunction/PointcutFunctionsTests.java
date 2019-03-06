package com.smart.aspectj.pointcutfunction;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.advice.Waiter;

public class PointcutFunctionsTests {

	@Test
	public void testAnnotationPointcutFunction() {
		String xmlConfigPath = "com/smart/aspectj/pointcutfunction/aop-beans.xml";
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(xmlConfigPath);
		
		Waiter naiveWaiter = (Waiter) ctx.getBean("naiveWaiter");
		Waiter naughtyWaiter = (Waiter) ctx.getBean("naughtyWaiter");
		
		naiveWaiter.greetTo("John");
		naughtyWaiter.greetTo("Tom");
		
		ctx.close();
		
		assertTrue(true);
	}

}
