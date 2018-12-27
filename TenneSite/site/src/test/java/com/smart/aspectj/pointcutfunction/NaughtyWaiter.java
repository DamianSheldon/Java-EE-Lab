package com.smart.aspectj.pointcutfunction;

import com.smart.advice.Waiter;
import com.smart.aspectj.anno.NeedTest;

public class NaughtyWaiter implements Waiter {

	@NeedTest
	@Override
	public void greetTo(String name) {
		System.out.println("NaughtyWaiter: waiter greet to " + name);
	}

	@Override
	public void serveTo(String name) {
		System.out.println("NaughtyWaiter: waiter serve to " + name);
	}

}
