package com.tenneshop.order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tenneshop.order.entity.CustomerOrder;
import com.tenneshop.order.repository.OrderRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = OrderRepositoryImpl.class)
@EnableTransactionManagement
public class OrderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@PersistenceContext private EntityManager em;
	
	@Bean
	public JpaEntityInformation<CustomerOrder, ?> entityInformation() {
		return JpaEntityInformationSupport.getEntityInformation(CustomerOrder.class, em);
	}
	
	@Autowired
	private OrderRepositoryImpl<CustomerOrder, String> orderRepositoryImpl;
	
	@Override
	public void run(String... args) throws Exception {
		// Prepopulate orders for test
		orderRepositoryImpl.prepopulateTestOrders();
	}
}
