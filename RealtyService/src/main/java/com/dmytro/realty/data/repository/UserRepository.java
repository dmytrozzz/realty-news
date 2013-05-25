package com.dmytro.realty.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dmytro.realty.domain.RealtyUser;

public interface UserRepository extends CrudRepository<RealtyUser, Long> {
    RealtyUser findByLogin(String login);
    
    @Query("select user from RealtyUser user where user.id in :userIds and user.payed=true and user.enabled=true")
    List<RealtyUser> findPayedAndEnabled(@Param("userIds")List<Integer> userIds);
    
    @Query(value="SELECT user_id FROM news_feed WHERE criteria_id = :criteriaId", nativeQuery=true)
    List<Integer> findAllUserIds(@Param("criteriaId") long criteriaId);
}
