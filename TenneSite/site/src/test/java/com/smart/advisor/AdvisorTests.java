package com.smart.advisor;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.advice.Waiter;

public class AdvisorTests {
	
	private String xmlConfigPath = "com/smart/advisor/beans.xml";

	private ClassPathXmlApplicationContext ctx;
	
	@Before
    public void setUp() {
		ctx = new ClassPathXmlApplicationContext(xmlConfigPath);
    }
    
    @After
    public void tearDown() {
		ctx.close();
    }
	
	@Test
	public void testStaticMethodMatcherPointcutAdvisor() {
		Waiter waiter = (Waiter) ctx.getBean("waiter");
		waiter.greetTo("John");
		waiter.serveTo("Tom");
		
		Seller seller = (Seller) ctx.getBean("seller");
		seller.greetTo("Rod Johnson");
				
		assertTrue(true);
	}

	@Test
	public void testRegexpMethodPointcutAdvisor() {

		Waiter waiter = (Waiter) ctx.getBean("waiter1");
		waiter.greetTo("John");
		waiter.serveTo("Tom");
				
		assertTrue(true);
	}
	
	@Test
	public void testDynamicAdvisor() throws Exception {		
		Waiter waiter = (Waiter) ctx.getBean("waiter2");
		
		waiter.serveTo("Peter");
		waiter.greetTo("Peter");
		
		waiter.serveTo("John");
		waiter.greetTo("John");
				
		assertTrue(true);
	}
	
	@Test
	public void testControlFlowAdvisor() throws Exception {
		String xmlConfigPath = "com/smart/advisor/beans.xml";
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(xmlConfigPath);
		
		Waiter waiter = (Waiter) ctx.getBean("waiter3");
		
		waiter.serveTo("Peter");
		waiter.greetTo("Peter");
		
		WaiterDelegate wd = new WaiterDelegate();
		wd.setWaiter(waiter);
		
		wd.service("Peter");
		
		ctx.close();
		
		assertTrue(true);
	}
	
	@Test
	public void testComposableAdvisor() throws Exception {
		String xmlConfigPath = "com/smart/advisor/beans.xml";
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(xmlConfigPath);
		
		Waiter waiter = (Waiter) ctx.getBean("waiter4");
		
		waiter.serveTo("Peter");
		waiter.greetTo("Peter");
		
		WaiterDelegate wd = new WaiterDelegate();
		wd.setWaiter(waiter);
		
		wd.service("Peter");
		
		ctx.close();
		
		assertTrue(true);
	}
	
	@Test
	public void testBeanNameAutoProxyCreator() throws Exception {
		String xmlConfigPath = "com/smart/advisor/beans.xml";
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(xmlConfigPath);
		
		Waiter waiter = (Waiter) ctx.getBean("waiter5");

		Seller seller = (Seller) ctx.getBean("seller5");
		
		waiter.greetTo("John");
		
		seller.greetTo("Tom");
		
		ctx.close();
		
		assertTrue(true);
	}
	
}
