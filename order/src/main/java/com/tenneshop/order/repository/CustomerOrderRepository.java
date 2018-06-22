package com.tenneshop.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenneshop.order.entity.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
	List<CustomerOrder> findAllOrders(Integer orderId);
	
	CustomerOrder findOrderByOrderId(Integer orderId);
}
