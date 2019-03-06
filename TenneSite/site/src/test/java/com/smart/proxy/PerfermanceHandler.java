package com.smart.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerfermanceHandler implements InvocationHandler {

	private Object target;
	
	public PerfermanceHandler(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		/* Why can I call proxy.getClass() inside InvocationHandler's invoke() method?
		 * https://stackoverflow.com/questions/36097447/why-can-i-call-proxy-getclass-inside-invocationhandlers-invoke-method
		 */
//		System.out.println("proxy: " + proxy);
		
		PerfermanceMonitor.begin(target.getClass().getName() + "." + method.getName());
		
		Object result = method.invoke(target, args);
		
		PerfermanceMonitor.end();
		
		return result;
	}

}
