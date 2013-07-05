package com.dmytro.realty.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
    private Product.Type productType;

    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private Product.Operation operation;

    @Column(name = "location")
    @Enumerated(EnumType.STRING)
    private Product.Location location = Product.Location.ALL;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parameters_id")
    private RealtyParameters parameters = new RealtyParameters();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "offers_relation", joinColumns = @JoinColumn(name = "criteria_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "offer_id", nullable = false))
    private Set<RealtyOffer> offerSet = new HashSet<>();

    public Product.Type getProductType() {
        return productType;
    }

    public void setProductType(Product.Type productType) {
        this.productType = productType;
    }

    public RealtyParameters getParameters() {
        return parameters;
    }

    public void setParameters(RealtyParameters parameters) {
        this.parameters = parameters;
    }

    public Product.Operation getOperation() {
        return operation;
    }

    public void setOperation(Product.Operation operation) {
        this.operation = operation;
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

    public Product.Location getLocation() {
        return location;
    }

    public void setLocation(Product.Location location) {
        this.location = location;
    }

    public Set<RealtyOffer> getOfferSet() {
        return offerSet;
    }
}
