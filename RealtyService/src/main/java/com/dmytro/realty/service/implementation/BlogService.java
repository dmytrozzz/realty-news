package com.dmytro.realty.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmytro.realty.data.repository.BlogRepository;
import com.dmytro.realty.domain.BlogPost;
import com.dmytro.realty.service.IBlogService;

@Service("blogService")
public class BlogService implements IBlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	@Override
	public List<BlogPost> getAllPosts() {
		List<BlogPost> blog = new ArrayList<>();
		CollectionUtils.addAll(blog, blogRepository.findAll().iterator());
		return blog;
	}

	@Override
	public BlogPost getPost(long id) {
		return blogRepository.findOne(id);
	}

	@Override
	public BlogPost getPost(String suffix) {
		return blogRepository.findBySuffix(suffix);
	}
}
