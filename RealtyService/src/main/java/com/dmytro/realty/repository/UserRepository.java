package com.dmytro.realty.repository;

import org.springframework.data.repository.CrudRepository;

import com.dmytro.realty.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
