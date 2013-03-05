package com.dmytro.realty.domain.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;

@Entity
@Table(name = "search_criteria")
public class SearchCriteria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "criteria_seq")
    @SequenceGenerator(name = "criteria_seq", sequenceName = "search_criteria_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    //private Collection<RealtyUser> userSet;

    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    // @OneToMany
    @ElementCollection(targetClass = OperationType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "search_operations", joinColumns = @JoinColumn(name = "cetagory_id"))
    // @Column(name = "operation_type")
    private Collection<OperationType> operations = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "parameters_id")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public Collection<RealtyUser> getUserSet() {
//        return userSet;
//    }
//
//    public void setUserSet(Collection<RealtyUser> userSet) {
//        this.userSet = userSet;
//    }       
}
