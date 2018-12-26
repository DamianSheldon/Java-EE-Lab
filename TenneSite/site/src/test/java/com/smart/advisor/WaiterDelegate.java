package com.smart.advisor;

import com.smart.advice.Waiter;

public class WaiterDelegate {
	private Waiter waiter;
	
	public void service(String clientName) {
		waiter.greetTo(clientName);
		waiter.serveTo(clientName);
	}
	
	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}
}
