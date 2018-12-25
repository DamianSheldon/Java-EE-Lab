package com.smart.proxy;

public class ForumServiceImpl implements ForumService {

	@Override
	public void removeTopic(int topicId) {
		PerfermanceMonitor.begin("com.smart.proxy.ForumServiceImpl.removeTopic");
		
		System.out.println("Analog remove topic: " + topicId);
		
		try {
			Thread.currentThread();
			Thread.sleep(20);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		PerfermanceMonitor.end();
	}

	@Override
	public void removeForum(int forumId) {
		PerfermanceMonitor.begin("com.smart.proxy.ForumServiceImpl.removeForum");
		
		System.out.println("Analog remove forum: " + forumId);
		
		try {
			Thread.currentThread();
			Thread.sleep(40);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		PerfermanceMonitor.end();
	}

}
