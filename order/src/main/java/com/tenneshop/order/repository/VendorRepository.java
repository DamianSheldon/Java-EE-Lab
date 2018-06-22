package com.tenneshop.order.repository;

import org.springframework.data.repository.CrudRepository;

import com.tenneshop.order.entity.Vendor;

public interface VendorRepository extends CrudRepository<Vendor, Integer> {
	Vendor findByVendorId(Integer vendorId);
}
