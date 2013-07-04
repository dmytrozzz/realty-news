package com.dmytro.realty.data.repository;

import com.dmytro.realty.domain.RealtyUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<RealtyUser, Long> {
    RealtyUser findByLogin(String login);

    @Query("select user from RealtyUser user where user.id in :userIds and user.payed=true and user.enabled=true")
    List<RealtyUser> findPayedAndEnabled(@Param("userIds") List<Integer> userIds);

    @Query("select user.id from RealtyUser user where user.payed=true and user.enabled=true")
    List<Long> findPayedAndEnabled();

    @Query(value = "SELECT user_id FROM news_feed WHERE criteria_id = :criteriaId", nativeQuery = true)
    List<Long> findAllUserIds(@Param("criteriaId") long criteriaId);

    @Query(value = "select * from realty_user where billing_id = :billingID", nativeQuery = true)
    RealtyUser findByBillingId(@Param("billingID") long uniqueID);

    RealtyUser findByLoginAndEmail(String login, String email);

}
