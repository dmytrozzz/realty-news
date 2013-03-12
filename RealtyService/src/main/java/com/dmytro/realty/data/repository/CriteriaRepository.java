package com.dmytro.realty.data.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyParameters;

public interface CriteriaRepository extends CrudRepository<RealtyCriteria, Long> {

    @Query(value = "SELECT * FROM realty_search_criteria WHERE product_type = :prodType and parameters_id = :params", nativeQuery = true)
    Collection<RealtyCriteria> nativeFindBy(@Param("prodType") String productType, @Param("params") long parameters);

    @Query(value = "SELECT operation_type FROM realty_operations WHERE criteria_id = :critId", nativeQuery = true)
    public Collection<String> findByCriteriaId(@Param("critId") long critId);
    
    //TODO request with specific user fetching(only payed and enabled)
}
