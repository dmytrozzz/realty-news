package com.dmytro.realty.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "news_feed")
public class NewsFeed {
    @Id
    @Generated(value = GenerationTime.INSERT)
    // @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "approved")
    private boolean approved;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public boolean isApproved() {
	return approved;
    }

    public void setApproved(boolean approved) {
	this.approved = approved;
    }
}
