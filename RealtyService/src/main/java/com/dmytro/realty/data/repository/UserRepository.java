package com.dmytro.realty.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.dmytro.realty.domain.RealtyUser;

public interface UserRepository extends CrudRepository<RealtyUser, Long> {
    RealtyUser findByLogin(String login);
}
