package com.tenneshop.order.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tenneshop.order.entity.CustomerOrder;

public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {
	List<CustomerOrder> findAllOrdersById(String orderId);
}
