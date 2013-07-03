package com.dmytro.realty.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dmytro.realty.domain.BlogPost;

import java.util.List;

public interface BlogRepository extends
		PagingAndSortingRepository<BlogPost, Long> {
	
	BlogPost findBySuffix(String suffix);

    List<BlogPost> findAll();
}
