package com.tenneshop.order.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.tenneshop.order.entity.CustomerOrder;

@NoRepositoryBean
public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {
	List<CustomerOrder> findAllOrdersByOrderId(String orderId);
}
