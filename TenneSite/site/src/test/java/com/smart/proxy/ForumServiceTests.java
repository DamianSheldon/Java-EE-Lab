package com.smart.proxy;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class ForumServiceTests {

	@Test
	public void testForumService() {
		ForumServiceImpl forumService = new ForumServiceImpl();
		
		forumService.removeTopic(8);
		forumService.removeForum(1024);
		
		assertTrue(true);
	}

	@Test
	public void testJDKProxy() {
		ForumServicePureBusinessVersionImpl forumService = new ForumServicePureBusinessVersionImpl();
		
		PerfermanceHandler perfermanceHandler = new PerfermanceHandler(forumService);
		
		ForumService forumServiceProxy = (ForumService)Proxy
				.newProxyInstance(forumService.getClass().getClassLoader(), 
				forumService.getClass().getInterfaces(), perfermanceHandler);
		
		forumServiceProxy.removeTopic(8);
		forumServiceProxy.removeForum(1024);
		
		assertTrue(true);
	}
	
	@Test
	public void testCGLibProxy() {
		CglibProxy proxy = new CglibProxy();
		
		ForumServicePureBusinessVersionImpl forumServiceProxy = (ForumServicePureBusinessVersionImpl)proxy.getProxy(ForumServicePureBusinessVersionImpl.class);
		
		forumServiceProxy.removeTopic(8);
		forumServiceProxy.removeForum(1024);
		
		assertTrue(true);
	}
	
}
