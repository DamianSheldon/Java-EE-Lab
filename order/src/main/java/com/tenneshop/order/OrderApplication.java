package com.tenneshop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tenneshop.order.entity.CustomerOrder;
import com.tenneshop.order.repository.OrderRepositoryImpl;

@SpringBootApplication
//@EnableJpaRepositories(repositoryBaseClass = OrderRepositoryImpl.class)
public class OrderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

//	@Autowired
//	private OrderRepositoryImpl<CustomerOrder, String> orderRepositoryImpl;
	
	@Override
	public void run(String... args) throws Exception {
		// Prepopulate orders for test
//		orderRepositoryImpl.prepopulateTestOrders();
	}
}
