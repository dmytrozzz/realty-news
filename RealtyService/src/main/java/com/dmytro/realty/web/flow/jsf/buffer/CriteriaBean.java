package com.dmytro.realty.web.flow.jsf.buffer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyParameters;
import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;

public class CriteriaBean implements Serializable {

    private ProductType productType;

    private OperationType operation;

    private RealtyParameters parameters;

    public CriteriaBean() {
	this.parameters = new RealtyParameters();
    }

    public CriteriaBean(RealtyCriteria criteria) {
	this.productType = criteria.getProductType();
	this.operation = criteria.getOperation();
	this.parameters = criteria.getParameters();
    }

    public ProductType getProductType() {
	return productType;
    }

    public void setProductType(ProductType productType) {
	this.productType = productType;
    }

    public OperationType getOperation() {
		return operation;
	}

	public void setOperation(OperationType operation) {
		this.operation = operation;
	}

	public RealtyParameters getParameters() {
	return parameters;
    }

    public void setParameters(RealtyParameters parameters) {
	this.parameters = parameters;
    }

    public RealtyCriteria getRealtyCriteria() {
	RealtyCriteria realtyCriteria = new RealtyCriteria();
	realtyCriteria.setOperation(operation);
	realtyCriteria.setParameters(parameters);
	realtyCriteria.setProductType(productType);
	return realtyCriteria;
    }
}
