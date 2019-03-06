package com.smart.advice;

public class NaiveWaiter implements Waiter {

	@Override
	public void greetTo(String name) {
		System.out.println("waiter greet to " + name + " ...");
	}

	@Override
	public void serveTo(String name) {
		System.out.println("waiter serve to " + name + " ...");
	}

	public void smile(String customer, int times) {
		System.out.println("waiter smile to " + customer + " " + times + " times");
	}
	
}
