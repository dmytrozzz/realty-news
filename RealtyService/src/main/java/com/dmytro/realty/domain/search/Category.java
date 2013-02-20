package com.dmytro.realty.domain.search;

import java.io.Serializable;

import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;

public class Category implements Serializable {

    private ProductType productType;
    private OperationType operationType;

    private Parameters criteriaParameters = new Parameters();

    public ProductType getRealtyUnit() {
	return productType;
    }

    public void setRealtyUnit(ProductType realtyUnit) {
	this.productType = realtyUnit;
    }

    public OperationType getOperationType() {
	return operationType;
    }

    public void setOperationType(OperationType operationType) {
	this.operationType = operationType;
    }

    public Parameters getCriteriaParameters() {
	return criteriaParameters;
    }

    public void setCriteriaParameters(Parameters criteriaParameters) {
	this.criteriaParameters = criteriaParameters;
    }
}
