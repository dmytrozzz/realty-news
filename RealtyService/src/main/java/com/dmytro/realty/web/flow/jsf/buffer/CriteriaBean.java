package com.dmytro.realty.web.flow.jsf.buffer;

import com.dmytro.realty.domain.Location;
import com.dmytro.realty.domain.Product;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyParameters;

import java.io.Serializable;
import java.util.*;

public class CriteriaBean implements Serializable {

    private Product.Type productType;

    private Product.Operation operation;

    private RealtyParameters parameters;

    private List<String> locations;

    public CriteriaBean() {
        this.parameters = new RealtyParameters();
        locations = new LinkedList<>();
    }

    public CriteriaBean(RealtyCriteria criteria) {
        this.productType = criteria.getProductType();
        this.operation = criteria.getOperation();
        this.parameters = criteria.getParameters();
        locations = new LinkedList<>();
        for(Location l : criteria.getLocations())
            locations.add(l.getLocation().name());
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

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public RealtyCriteria getRealtyCriteria() {
        RealtyCriteria realtyCriteria = new RealtyCriteria();
        realtyCriteria.setOperation(operation);
        realtyCriteria.setParameters(parameters);
        realtyCriteria.setProductType(productType);
        Set<Location> locationList = new HashSet<>();
        for(String l : locations)
        locationList.add(new Location(Product.Location.valueOf(l)));
        realtyCriteria.setLocations(locationList);
        return realtyCriteria;
    }
}
