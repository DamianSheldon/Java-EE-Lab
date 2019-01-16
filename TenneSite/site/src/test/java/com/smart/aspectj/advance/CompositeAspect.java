package com.smart.aspectj.advance;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class CompositeAspect {
	
	@After("within(com.smart.*) && execution(* greetTo(..))")
	public void greetToFunc() {
		System.out.println("-- greetToFunc() executed! --");
	}
	
	@Before(" !target(com.smart.advice.NaiveWaiter) && execution(* serveTo(..))")
	public void notServeInNaiveWaiter() {
		System.out.println("-- notServeInNaiveWaiter() executed! --");
	}
	
	@AfterReturning("target(com.smart.advice.Waiter) || target(com.smart.aspectj.example.merchant)")
	public void waiterOrMerchant() {
		System.out.println("-- waiterOrMerchant() executed! --");
	}
	
}
