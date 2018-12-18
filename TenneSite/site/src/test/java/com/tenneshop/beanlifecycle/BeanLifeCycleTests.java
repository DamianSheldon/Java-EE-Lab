package com.tenneshop.beanlifecycle;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BeanLifeCycleTests {

	@Test
	public void testBeanLifeCycleInBeanFactory() {
		Resource res = new ClassPathResource("com/tenneshop/beanlifecycle/beans.xml");
		
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
		reader.loadBeanDefinitions(res);
		
		bf.addBeanPostProcessor(new MyBeanPostProcessor());
		
		bf.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		
		Car car1 = (Car) bf.getBean("car");
		assertNotNull(car1);
		assertEquals(car1.getBrand(), "RedFlagCA72");
		car1.introduce();
		car1.setColor("Red");
		
		Car car2 = (Car) bf.getBean("car");
		
		System.out.println("car1 == car2: " + (car1 == car2));
		
		bf.destroySingletons();
	}

	@Test
	public void testBeanLifeCycleInApplicationContext() throws Exception {
		ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("com/tenneshop/beanlifecycle/application-context-beans.xml");
		
		Car car1 = (Car) appCtx.getBean("car");
		
		assertNotNull(car1);
		assertNotEquals(car1.getBrand(), "RedFlagCA72");
		assertEquals(car1.getBrand(), "CheryQQ");
		car1.introduce();
		car1.setColor("Red");
		
		Car car2 = (Car) appCtx.getBean("car");
		
		System.out.println("car1 == car2: " + (car1 == car2));
		
		appCtx.close();
	}
}
