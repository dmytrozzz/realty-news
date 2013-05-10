package com.dmytro.realty.service;

import java.util.List;

import com.dmytro.realty.domain.BlogPost;

public interface IBlogService {
	
	List<BlogPost> getAllPosts();
	
	BlogPost getPost(long id);
	
	BlogPost getPost(String suffix);

}
