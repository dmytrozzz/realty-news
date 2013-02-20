package com.dmytro.realty.domain.search;

import com.dmytro.realty.domain.User;

public class SearchCriteria {
    private Category category;
    private Parameters parameters;

    private User user;

    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

    public Parameters getParameters() {
	return parameters;
    }

    public void setParameters(Parameters parameters) {
	this.parameters = parameters;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }
}
