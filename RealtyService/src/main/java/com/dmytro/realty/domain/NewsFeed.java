package com.dmytro.realty.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dmytro.realty.domain.search.SearchCriteria;

@Entity
@Table(name = "news_feed")
public class NewsFeed {
    private long id;

    private Collection<User> userCollection;
    private Collection<SearchCriteria> criteriaCollection;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public Collection<User> getUserCollection() {
	return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
	this.userCollection = userCollection;
    }

    public Collection<SearchCriteria> getCriteriaCollection() {
	return criteriaCollection;
    }

    public void setCriteriaCollection(Collection<SearchCriteria> criteriaCollection) {
	this.criteriaCollection = criteriaCollection;
    }
}
