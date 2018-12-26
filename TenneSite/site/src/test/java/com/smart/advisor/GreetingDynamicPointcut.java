package com.smart.advisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import com.smart.advice.Waiter;

public class GreetingDynamicPointcut extends DynamicMethodMatcherPointcut {

	private static List<String> speacialClientList = new ArrayList<String>();
	static {
		speacialClientList.add("John");
		speacialClientList.add("Tom");
	}
	
	@Override
	public ClassFilter getClassFilter() {
		return new ClassFilter() {

			@Override
			public boolean matches(Class<?> clazz) {
				System.out.println("Invoke getClassFilter() make static check for " + clazz.getName()); 
				
				return Waiter.class.isAssignableFrom(clazz);
			}
			
		};
	}

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		System.out.println("Invoke matches(method, targetClass) make static check for " + method.getName());
		
		return "greetTo".equals(method.getName());
	}
	
	@Override
	public boolean matches(Method method, Class<?> targetClass, Object... args) {
		System.out.println("Invoke matches(method, targetClass, args) make static check for " + method.getName());

		String clientName = (String) args[0];
		
		return speacialClientList.contains(clientName);
	}

}
