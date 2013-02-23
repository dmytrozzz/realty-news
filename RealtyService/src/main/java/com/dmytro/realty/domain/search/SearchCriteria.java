package com.dmytro.realty.domain.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;

public class SearchCriteria implements Serializable {
    private ProductType productType;
    private Collection<OperationType> operations = new ArrayList<>();
    private Parameters parameters = new Parameters();   

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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
