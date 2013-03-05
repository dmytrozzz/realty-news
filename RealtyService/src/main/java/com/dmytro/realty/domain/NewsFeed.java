package com.dmytro.realty.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.dmytro.realty.domain.search.SearchCriteria;

@Entity
@Table(name = "news_feed")
public class NewsFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Generated(value = GenerationTime.INSERT)
    @Column(name = "id")
    private long id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "news_feeds_user_relation", joinColumns = @JoinColumn(name = "news_feed_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<RealtyUser> userCollection;

    @OneToOne
    @JoinColumn(name = "criteria_id")
    private SearchCriteria criteria;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public Collection<RealtyUser> getUserCollection() {
	return userCollection;
    }

    public void setUserCollection(Collection<RealtyUser> userCollection) {
	this.userCollection = userCollection;
    }

    public SearchCriteria getCriteria() {
	return criteria;
    }

    public void setCriteria(SearchCriteria criteria) {
	this.criteria = criteria;
    }
}