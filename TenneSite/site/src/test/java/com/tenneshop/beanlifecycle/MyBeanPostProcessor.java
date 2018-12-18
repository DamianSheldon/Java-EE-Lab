package com.tenneshop.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if ("car".equals(beanName)) {
			Car car = (Car)bean;
			if (car.getColor() == null) {
				
				System.out.println("Invoke BeanPostProcessor.postProcessBeforeInitialization, "
						+ "color is null so set it to black");
				car.setColor("black");
			}
		}
		
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if ("car".equals(beanName)) {
			Car car = (Car)bean;
			if (car.getMaxSpeed() >= 200) {
				
				System.out.println("Invoke BeanPostProcessor.postProcessAfterInitialization, "
						+ "adjust max speed to 200");
				car.setMaxSpeed(200);
			}
		}
		
		return bean;
	}

}
