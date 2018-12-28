package com.smart.aspectj.advance;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BindJoinPointParamsAspect {
	
	@Before("target(com.smart.advice.NaiveWaiter) && args(name, num, ..)")
	public void logJoinPointParams(int num, String name) {
		System.out.println("-- begin login join point params --");
		
		System.out.println("num: " + num);
		System.out.println("name: " + name);
		
		System.out.println("-- end login join point params --");
	}
}
