package com.dmytro.realty.data.repository;

import com.dmytro.realty.domain.RealtyOffer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 05.07.13
 * Time: 18:43
 * To change this template use File | Settings | File Templates.
 */
public interface RealtyOfferRepository extends CrudRepository<RealtyOffer, Long> {
}
