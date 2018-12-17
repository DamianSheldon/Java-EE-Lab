package com.tenneshop.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

	private String brand;
	private String color;
	private int maxSpeed;
	
	private BeanFactory beanFactory;
	private String beanName;

	public Car() {
		System.out.println("Invoke Car() ...");
	}
	
	public void setBrand(String brand) {
		System.out.println("Invoke setBrand() ...");
		
		this.brand = brand;
	}
	
	// BeanFactoryAware
	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		
		System.out.println("Invoke BeanFactoryAware setBeanFactory ...");

		beanFactory = arg0;
	}
	
	// BeanNameAware
	@Override
	public void setBeanName(String arg0) {
		System.out.println("Invoke BeanNameAware setBeanName ...");
		
		beanName = arg0;
	}
	
	// InitializingBean
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
	
	// DisposableBean
	@Override
	public void destroy() throws Exception {
		
	}
	
}
