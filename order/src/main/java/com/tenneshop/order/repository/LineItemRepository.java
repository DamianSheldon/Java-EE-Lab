package com.tenneshop.order.repository;

import org.springframework.data.repository.CrudRepository;

import com.tenneshop.order.entity.LineItem;
import com.tenneshop.order.entity.LineItemKey;

public interface LineItemRepository extends CrudRepository<LineItem, LineItemKey> {

}
