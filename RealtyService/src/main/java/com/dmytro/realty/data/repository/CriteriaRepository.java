package com.dmytro.realty.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dmytro.realty.domain.RealtyCriteria;

public interface CriteriaRepository extends CrudRepository<RealtyCriteria, Long> {

    @Query(value = "SELECT * FROM realty_search_criteria WHERE product_type = :prodType and operation_type = :opType and location = :location and parameters_id = :params", nativeQuery = true)
    RealtyCriteria nativeFindBy(@Param("prodType") String productType, @Param("opType") String operationType,@Param("location") String location,@Param("params") long parameters);
}
