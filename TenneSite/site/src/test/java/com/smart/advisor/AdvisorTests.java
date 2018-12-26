package com.smart.advisor;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.advice.Waiter;

public class AdvisorTests {

	@Test
	public void testStaticMethodMatcherPointcutAdvisor() {
		String xmlConfigPath = "com/smart/advisor/beans.xml";
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(xmlConfigPath);
		
		Waiter waiter = (Waiter) ctx.getBean("waiter");
		waiter.greetTo("John");
		waiter.serveTo("Tom");
		
		Seller seller = (Seller) ctx.getBean("seller");
		seller.greetTo("Rod Johnson");
		
		ctx.close();
		
		assertTrue(true);
	}

}
