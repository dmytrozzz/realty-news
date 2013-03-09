package com.dmytro.realty.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;

@Entity
@Table(name = "realty_search_criteria")
public class RealtyCriteria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "criteria_seq")
    @SequenceGenerator(name = "criteria_seq", sequenceName = "search_criteria_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @ManyToMany(mappedBy = "criteriaCollection")
    private Collection<RealtyUser> userCollection;

    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @CollectionTable(name = "realty_operations", joinColumns = @JoinColumn(name = "criteria_id"))
    @Column(name = "operation_type")
    private Set<String> operations = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parameters_id")
    private RealtyParameters parameters = new RealtyParameters();

    public ProductType getProductType() {
	return productType;
    }

    public void setProductType(ProductType productType) {
	this.productType = productType;
    }

    public RealtyParameters getParameters() {
	return parameters;
    }

    public void setParameters(RealtyParameters parameters) {
	this.parameters = parameters;
    }

    public Collection<String> getOperations() {
	return operations;
    }

    public void setOperations(Set<String> operations) {
	this.operations = operations;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public Collection<RealtyUser> getUserCollection() {
	return userCollection;
    }

    public void setUserCollection(Collection<RealtyUser> userCollection) {
	this.userCollection = userCollection;
    }      
}
