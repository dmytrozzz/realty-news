package com.dmytro.realty.web.controller;

import java.util.List;

import com.dmytro.realty.data.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dmytro.realty.domain.BlogPost;
import com.dmytro.realty.service.IBlogService;

@Controller("blogController")
public class BlogController {
	@Autowired
	private IBlogService blogService;

	public List<BlogPost> getAllPosts() {
		return blogService.getAllPosts();
	}

	@RequestMapping(value = "blog/{suffix}", method = RequestMethod.GET)
	public String getBlogPost(@PathVariable("suffix") String suffix) {
		System.out.println("WE HERE!!!");
		return "/app/blog?singlePost=" + true + "&suffix=" + suffix;
	}
	
	public BlogPost getPost(String suffix) {
		return blogService.getPost(suffix);
	}

    public BlogPost createPost(){
        return new BlogPost();
    }

    public void savePost(BlogPost post) {
        blogService.savePost(post);
    }
}
