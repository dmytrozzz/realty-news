package com.dmytro.realty.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dmytro.realty.domain.FeedBack;

public interface FeedBackRepository extends CrudRepository<FeedBack, Long> {
	
	List<FeedBack> findAllByGood(boolean good);
}
