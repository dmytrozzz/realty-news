package com.dmytro.realty.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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

    @Transient
    private Collection<RealtyUser> userCollection;
    @Transient
    private Collection<SearchCriteria> criteriaCollection;

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

    public Collection<SearchCriteria> getCriteriaCollection() {
	return criteriaCollection;
    }

    public void setCriteriaCollection(Collection<SearchCriteria> criteriaCollection) {
	this.criteriaCollection = criteriaCollection;
    }
}
