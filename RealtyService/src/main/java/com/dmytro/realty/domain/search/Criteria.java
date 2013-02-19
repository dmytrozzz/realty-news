package com.dmytro.realty.domain.search;

import java.io.Serializable;

public class Criteria implements Serializable {

    private ProductType productType;
    private OperationType operationType;

    private CriteriaParameters criteriaParameters = new CriteriaParameters();

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

    public CriteriaParameters getCriteriaParameters() {
	return criteriaParameters;
    }

    public void setCriteriaParameters(CriteriaParameters criteriaParameters) {
	this.criteriaParameters = criteriaParameters;
    }
}
