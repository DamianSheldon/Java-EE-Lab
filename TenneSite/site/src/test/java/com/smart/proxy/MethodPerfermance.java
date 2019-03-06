package com.smart.proxy;

public class MethodPerfermance {

	private long begin;
	private long end;
	private String serviceMethod;
	
	public MethodPerfermance(String serviceMethod) {
		this.serviceMethod = serviceMethod;
		this.begin = System.currentTimeMillis();
	}
	
	public void printPerfermance() {
		end = System.currentTimeMillis();
		long elapse = end - begin;
		
		System.out.println(serviceMethod + " costs " + elapse + " ms");
	}
}
