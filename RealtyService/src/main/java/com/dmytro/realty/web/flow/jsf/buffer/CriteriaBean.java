package com.dmytro.realty.web.flow.jsf.buffer;

import com.dmytro.realty.domain.Product;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyParameters;

import java.io.Serializable;

public class CriteriaBean implements Serializable {

    private Product.Type productType;

    private Product.Operation operation;

    private RealtyParameters parameters;

    private Product.Location location;

    public CriteriaBean() {
        this.parameters = new RealtyParameters();
    }

    public CriteriaBean(RealtyCriteria criteria) {
        this.productType = criteria.getProductType();
        this.operation = criteria.getOperation();
        this.location = criteria.getLocation();
        this.parameters = criteria.getParameters();
    }

    public Product.Type getProductType() {
        return productType;
    }

    public void setProductType(Product.Type productType) {
        this.productType = productType;
    }

    public Product.Operation getOperation() {
        return operation;
    }

    public void setOperation(Product.Operation operation) {
        this.operation = operation;
    }

    public RealtyParameters getParameters() {
        return parameters;
    }

    public void setParameters(RealtyParameters parameters) {
        this.parameters = parameters;
    }

    public Product.Location getLocation() {
        return location;
    }

    public void setLocation(Product.Location location) {
        this.location = location;
    }

    public RealtyCriteria getRealtyCriteria() {
        RealtyCriteria realtyCriteria = new RealtyCriteria();
        realtyCriteria.setOperation(operation);
        realtyCriteria.setParameters(parameters);
        realtyCriteria.setProductType(productType);
        realtyCriteria.setLocation(location);
        return realtyCriteria;
    }
}
