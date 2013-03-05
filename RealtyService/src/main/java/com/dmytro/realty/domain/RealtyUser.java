package com.dmytro.realty.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "realty_user")
public class RealtyUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "realty_user_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "news_feed", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "criteria_id"))
    private Collection<RealtyCriteria> criteriaCollection;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Collection<RealtyCriteria> getCriteriaCollection() {
	return criteriaCollection;
    }

    public void setCriteriaCollection(Collection<RealtyCriteria> criteriaCollection) {
	this.criteriaCollection = criteriaCollection;
    }
}
