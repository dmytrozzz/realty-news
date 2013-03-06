package com.dmytro.realty.data.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyParameters;
import com.dmytro.realty.domain.search.enums.ProductType;

public interface CriteriaRepository extends CrudRepository<RealtyCriteria, Long> {

    @Query(value = "SELECT * FROM realty_search_criteria WHERE product_type = :prodType and parameters_id = :params", nativeQuery = true)
    Collection<RealtyCriteria> nativeFindBy(@Param("prodType") String productType, @Param("params") long parameters);

    @Query(value = "select crit from RealtyCriteria crit where crit.productType = :prodType and crit.parameters = :params "
	    + "and crit.operations = :opers")
    RealtyCriteria findBy(@Param("prodType") ProductType productType, @Param("params") RealtyParameters parameters,
	    @Param("opers") Collection<String> operations);

    @Query(value = "select crit from RealtyCriteria crit where crit.productType = :prodType and crit.parameters = :params")
    Collection<RealtyCriteria> findBy(@Param("prodType") ProductType productType,
	    @Param("params") RealtyParameters parameters);

    RealtyCriteria findByProductTypeAndParametersAndOperations(ProductType productType, RealtyParameters parameters,
	    Collection<String> operations);

    @Query(value = "SELECT operation_type FROM realty_operations WHERE criteria_id = :critId", nativeQuery = true)
    public Collection<String> findByCriteriaId(@Param("critId") long critId);
}
