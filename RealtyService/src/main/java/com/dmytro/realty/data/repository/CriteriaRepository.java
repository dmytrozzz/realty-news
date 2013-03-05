package com.dmytro.realty.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dmytro.realty.domain.search.RealtyCriteria;
import com.dmytro.realty.domain.search.RealtyParameters;
import com.dmytro.realty.domain.search.enums.ProductType;

public interface CriteriaRepository extends CrudRepository<RealtyCriteria, Long> {

    @Query("select c from RealtyCriteria c where c.productType = :prodType and c.parameters = :params")
    RealtyCriteria findBy(@Param("prodType") ProductType productType, @Param("params") RealtyParameters parameters);
}
