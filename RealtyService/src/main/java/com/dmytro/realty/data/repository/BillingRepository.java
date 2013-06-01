package com.dmytro.realty.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.dmytro.realty.domain.Billing;

public interface BillingRepository extends CrudRepository<Billing, Long> {   
	Billing findByUniqueID(String uniqueID);
}
