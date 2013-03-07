package com.dmytro.realty.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dmytro.realty.domain.RealtyParameters;

public interface ParametersRepository extends CrudRepository<RealtyParameters, Long> {

    @Query("select p from RealtyParameters p where p.fromPrice = :from and p.toPrice = :to")
    RealtyParameters findByParameters(@Param("from") int fromPrice, @Param("to") int toPrice);
}
