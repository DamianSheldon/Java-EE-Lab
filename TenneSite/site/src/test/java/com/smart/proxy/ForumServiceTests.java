package com.smart.proxy;

import static org.junit.Assert.*;

import org.junit.Test;

public class ForumServiceTests {

	@Test
	public void testForumService() {
		ForumServiceImpl forumService = new ForumServiceImpl();
		
		forumService.removeTopic(8);
		forumService.removeForum(1024);
		
		assertTrue(true);
	}

}
