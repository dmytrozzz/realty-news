package com.dmytro.realty.data.repository;

import com.dmytro.realty.domain.RealtyOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RealtyOfferRepository extends JpaRepository<RealtyOffer, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM realty_offer WHERE now() - add_time > '00:05:00'", nativeQuery = true)
    void refresh();

    @Transactional(readOnly = true)
    @Query(value = "select id, add_time, date, link, offender, phone, phonelink, price, content from realty_offer where id in " +
            "(select offer_id from offers_relation where criteria_id in (select distinct criteria_id from news_feed where user_id = :userId))", nativeQuery = true)
    List<RealtyOffer> getOffersByUser(@Param("userId") long userId);

    @Override
    @Transactional
    <S extends RealtyOffer> List<S> save(Iterable<S> ses);
}
