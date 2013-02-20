package com.dmytro.realty.repository;

import org.springframework.data.repository.CrudRepository;

import com.dmytro.realty.domain.NewsFeed;

public interface NewsFeedRepository extends CrudRepository<NewsFeed, Long> {
}
