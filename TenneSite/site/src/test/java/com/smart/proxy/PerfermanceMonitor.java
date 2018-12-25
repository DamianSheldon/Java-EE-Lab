package com.smart.proxy;

public class PerfermanceMonitor {
	
	private static ThreadLocal<MethodPerfermance> perfermanceRecord = new ThreadLocal<MethodPerfermance>();
	
	public static void begin(String method) {
		System.out.println("begin monitor ...");
		
		MethodPerfermance mp = new MethodPerfermance(method);
		perfermanceRecord.set(mp);
	}
	
	public static void end() {
		MethodPerfermance mp = perfermanceRecord.get();
		mp.printPerfermance();
		
		System.out.println("end monitor");
	}
}
