package com.dmytro.realty.service.implementation;

import com.dmytro.realty.data.repository.BlogRepository;
import com.dmytro.realty.domain.BlogPost;
import com.dmytro.realty.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("blogService")
public class BlogService implements IBlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<BlogPost> getAllPosts() {
        return blogRepository.findAll();
    }

    @Override
    public BlogPost getPost(long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public BlogPost getPost(String suffix) {
        return blogRepository.findBySuffix(suffix);
    }

    @Override
    public BlogPost savePost(BlogPost post) {
        return blogRepository.save(post);
    }
}
