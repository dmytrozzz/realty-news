package com.dmytro.realty.service;

import java.util.Collection;

import com.dmytro.realty.domain.NewsFeed;

public interface INewsFeedService {
    void addNewsFeed(NewsFeed newsFeed);

    void addNewsFeeds(Collection<NewsFeed> newsFeeds);

    NewsFeed getNewsFeed(long id);
}
