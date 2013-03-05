package com.dmytro.realty.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmytro.realty.data.repository.NewsFeedRepository;
import com.dmytro.realty.domain.NewsFeed;
import com.dmytro.realty.service.INewsFeedService;

@Service("newsFeedService")
@Repository
@Transactional
public class NewsFeedService implements INewsFeedService {

    @Autowired
    private NewsFeedRepository newsFRepository;

    @Override
    public void addNewsFeed(NewsFeed newsFeed) {
	newsFRepository.save(newsFeed);
    }

    @Override
    public void addNewsFeeds(Collection<NewsFeed> newsFeeds) {
	newsFRepository.save(newsFeeds);
    }

    @Override
    public NewsFeed getNewsFeed(long id) {
	return newsFRepository.findOne(id);
    }
}
