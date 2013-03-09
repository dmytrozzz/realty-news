package com.dmytro.realty.data.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyParameters;
import com.dmytro.realty.domain.search.enums.ProductType;

public interface CriteriaRepository extends CrudRepository<RealtyCriteria, Long> {

    @Query(value = "SELECT rsc FROM realty_search_criteria rsc WHERE rsc.product_type = :prodType and rsc.parameters_id = :params", nativeQuery = true)
    Collection<RealtyCriteria> nativeFindBy(@Param("prodType") String productType, @Param("params") long parameters);

    @Query(value = "SELECT operation_type FROM realty_operations WHERE criteria_id = :critId", nativeQuery = true)
    public Collection<String> findByCriteriaId(@Param("critId") long critId);
    
    //TODO request with specific user fetching(only payed and enabled)
}
