package com.smart.aspectj.advance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class NaiveWaiterGreetToAroundAspect {

	@Around("target(com.smart.advice.NaiveWaiter) && execution(* greetTo(..))")
	public void logDebugInfo(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("-- begin log join point info --");
		
		System.out.println("arg0: " + pjp.getArgs()[0]);
		System.out.println("signature: " + pjp.getTarget().getClass());
		
		pjp.proceed();
		
		System.out.println("-- end log join point info --");
	}
}
