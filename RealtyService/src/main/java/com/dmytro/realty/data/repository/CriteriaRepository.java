package com.dmytro.realty.data.repository;

import com.dmytro.realty.domain.RealtyCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CriteriaRepository extends JpaRepository<RealtyCriteria, Long> {
    @Query(value = "select id, operation_type, product_type,parameters_id, location from realty_search_criteria where id in (" +
            "select distinct criteria_id from news_feed where user_id in(" +
            "select id from realty_user where payed = true and enabled = true)" +
            ")", nativeQuery = true)
    Iterable<RealtyCriteria> findAllPayedAndEnabled();

    @Query(value = "SELECT * FROM realty_search_criteria WHERE product_type = :prodType and operation_type = :opType and location = :location and parameters_id = :params", nativeQuery = true)
    RealtyCriteria nativeFindBy(@Param("prodType") String productType, @Param("opType") String operationType,@Param("location") String location,@Param("params") long parameters);
}
