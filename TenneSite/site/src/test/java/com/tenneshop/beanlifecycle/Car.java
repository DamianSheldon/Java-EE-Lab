package com.tenneshop.beanlifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
	
	@SuppressWarnings("unused")
	private BeanFactory beanFactory;
	
	@SuppressWarnings("unused")
	private String beanName;

	public Car() {
		System.out.println("Invoke Car() ...");
	}
	
	public void setBrand(String brand) {
		System.out.println("Invoke setBrand() ...");
		
		this.brand = brand;
	}
	
	public void introduce() {
		System.out.println("brand: " + brand + ";color: " + color + ";maxSpeed: " + maxSpeed);
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
		System.out.println("Invoke InitializingBean afterPropertiesSet ...");
	}
	
	// DisposableBean
	@Override
	public void destroy() throws Exception {
		System.out.println("Invoke DisposableBean destroy ...");
	}
	
	@PostConstruct
	public void myInit() {
		System.out.println("Invoke myInit() set maxSpeed to 240 ...");
		
		maxSpeed = 240;
	}
	
	@PreDestroy
	public void myDestroy() {
		System.out.println("Invoke myDestroy() ...");
	}
}
