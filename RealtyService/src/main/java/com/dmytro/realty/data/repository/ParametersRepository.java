package com.dmytro.realty.data.repository;

import com.dmytro.realty.domain.RealtyParameters;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ParametersRepository extends CrudRepository<RealtyParameters, Long> {

    @Query("select p from RealtyParameters p where p.fromPrice = :from and p.toPrice = :to and p.fromRooms = :fromR and p.toRooms = :toR")
    RealtyParameters findByParameters(@Param("from") int fromPrice, @Param("to") int toPrice, @Param("fromR") int fromRooms, @Param("toR") int toRooms);
}
