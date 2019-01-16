package com.smart.proxy;

public class ForumServicePureBusinessVersionImpl implements ForumService {
	@Override
	public void removeTopic(int topicId) {		
		System.out.println("Analog remove topic: " + topicId);
		
		try {
			Thread.currentThread();
			Thread.sleep(20);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void removeForum(int forumId) {		
		System.out.println("Analog remove forum: " + forumId);
		
		try {
			Thread.currentThread();
			Thread.sleep(40);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
