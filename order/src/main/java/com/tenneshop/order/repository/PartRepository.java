package com.tenneshop.order.repository;

import org.springframework.data.repository.CrudRepository;

import com.tenneshop.order.entity.Part;
import com.tenneshop.order.entity.PartKey;

public interface PartRepository extends CrudRepository<Part, PartKey> {
	Part findByPartNumberAndRevision(String partNumber, Integer revision);
}
