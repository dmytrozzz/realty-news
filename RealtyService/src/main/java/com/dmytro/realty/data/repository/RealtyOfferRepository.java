package com.dmytro.realty.data.repository;

import com.dmytro.realty.domain.RealtyOffer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RealtyOfferRepository extends CrudRepository<RealtyOffer, Long> {

    @Modifying
    @Query(value="DELETE FROM realty_offer WHERE now() - add_time > '00:05:00'", nativeQuery = true)
    void refresh();

    @Query(value = "select id, add_time, date, link, offender, phone, phonelink, price, criteria_id, content from realty_offer where criteria_id in " +
            "(select distinct criteria_id from news_feed where user_id = :userId)", nativeQuery = true)
    List<RealtyOffer> getOffersByUser(@Param("userId") long userId);
}
