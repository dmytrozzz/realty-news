package com.dmytro.realty.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dmytro.realty.domain.Billing;
import org.springframework.data.repository.query.Param;

public interface BillingRepository extends CrudRepository<Billing, Long> {
	Billing findByUniqueID(String uniqueID);
}
