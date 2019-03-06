package com.smart.aspectj.example;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.advice.Waiter;

public class DeclareParentsTests {

	@Test
	public void testDeclareParents() {
		String xmlConfigPath = "com/smart/aspectj/example/aop-beans.xml";
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(xmlConfigPath);
		
		Waiter waiter = (Waiter) ctx.getBean("waiter");
		waiter.greetTo("John");
		
		Merchant merchant = (Merchant) waiter;
		merchant.sell("John", "Beer");
		
		ctx.close();
		
		assertTrue(true);
	}

}
