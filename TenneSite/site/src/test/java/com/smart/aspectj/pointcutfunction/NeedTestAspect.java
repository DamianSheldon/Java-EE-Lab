package com.smart.aspectj.pointcutfunction;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class NeedTestAspect {

	@AfterReturning("@annotation(com.smart.aspectj.anno.NeedTest)")
	public void needTest() {
		System.out.println("needTest function executed!");
	}
}
