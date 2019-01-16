package com.smart.advisor;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.smart.advice.Waiter;

public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1292816060211394559L;

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return "greetTo".equals(method.getName());
	}

	@Override
	public ClassFilter getClassFilter() {
		return new ClassFilter() {

			@Override
			public boolean matches(Class<?> clazz) {
				return Waiter.class.isAssignableFrom(clazz);
			}
			
		};
	}
	
}
