package com.dmytro.realty.web.controller;

import com.dmytro.realty.domain.BlogPost;
import com.dmytro.realty.service.IBlogService;
import com.dmytro.realty.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller("blogController")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IUserService userService;

    public List<BlogPost> getAllPosts() {
        return blogService.getAllPosts();
    }

    @RequestMapping(value = "blog/{suffix}", method = RequestMethod.GET)
    public String getBlogPost(@PathVariable("suffix") String suffix) {
        return "/app/blog?singlePost=" + true + "&suffix=" + suffix;
    }

    @RequestMapping(value = "blog/edit/{suffix}", method = RequestMethod.GET)
    public String getBlogPostEdit(@PathVariable("suffix") String suffix) {
        return "/app/blog?singlePost=" + true + "&modify=" + true + "&suffix=" + suffix;
    }

    public BlogPost getPost(String suffix) {
        return blogService.getPost(suffix);
    }

    public BlogPost createPost() {
        return new BlogPost();
    }

    public void savePost(BlogPost post) {
        post.setAuthor(userService.getCurrentUser().getLogin());
        blogService.savePost(post);
    }

    public void deletePost(BlogPost post) {
        blogService.deletePost(post);
    }
}
