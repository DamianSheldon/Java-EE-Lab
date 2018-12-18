package com.tenneshop.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition bd = beanFactory.getBeanDefinition("car");
		
		bd.getPropertyValues().addPropertyValue("brand", "CheryQQ");
		
		System.out.println("Invoke BeanFactoryPostProcessor postProcessBeanFactory");
	}

}
