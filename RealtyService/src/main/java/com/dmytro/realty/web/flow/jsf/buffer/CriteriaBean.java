package com.dmytro.realty.web.flow.jsf.buffer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyParameters;
import com.dmytro.realty.domain.search.enums.ProductType;

public class CriteriaBean implements Serializable {

    private ProductType productType;

    private List<String> operations;

    private RealtyParameters parameters;

    public CriteriaBean() {
	this.operations = new ArrayList<>();
	this.parameters = new RealtyParameters();
    }

    public CriteriaBean(RealtyCriteria criteria) {
	this.productType = criteria.getProductType();
	this.operations = new ArrayList<>(criteria.getOperations());
	this.parameters = criteria.getParameters();
    }

    public ProductType getProductType() {
	return productType;
    }

    public void setProductType(ProductType productType) {
	this.productType = productType;
    }

    public List<String> getOperations() {
	return operations;
    }

    public void setOperations(List<String> operations) {
	this.operations = operations;
    }

    public RealtyParameters getParameters() {
	return parameters;
    }

    public void setParameters(RealtyParameters parameters) {
	this.parameters = parameters;
    }

    public RealtyCriteria getRealtyCriteria() {
	RealtyCriteria realtyCriteria = new RealtyCriteria();
	realtyCriteria.getOperations().addAll(operations);
	realtyCriteria.setParameters(parameters);
	realtyCriteria.setProductType(productType);
	return realtyCriteria;
    }
}
