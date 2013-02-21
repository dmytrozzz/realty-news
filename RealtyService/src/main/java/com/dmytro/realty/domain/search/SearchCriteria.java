package com.dmytro.realty.domain.search;


import java.util.Collection;

import com.dmytro.realty.domain.search.enums.OperationType;

public class SearchCriteria {
    private Category category;
    private Collection<OperationType> operations;
    private Parameters parameters;    

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

    public Collection<OperationType> getOperations() {
        return operations;
    }

    public void setOperations(Collection<OperationType> operations) {
        this.operations = operations;
    }
}
