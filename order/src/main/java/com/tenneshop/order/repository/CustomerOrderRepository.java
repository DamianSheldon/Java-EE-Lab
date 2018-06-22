package com.tenneshop.order.repository;

//import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tenneshop.order.entity.CustomerOrder;

public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long> {
//	List<CustomerOrder> findAllOrders();
	
	CustomerOrder findOrderByOrderId(Integer orderId);
}
