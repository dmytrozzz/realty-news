package com.dmytro.realty.domain.search;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "realty_parameters")
public class RealtyParameters implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "param_seq")
    @SequenceGenerator(name = "param_seq", sequenceName = "search_parameters_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "price_from")
    private int fromPrice = 1000;

    @Column(name = "price_to")
    private int toPrice = 5000;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public int getFromPrice() {
	return fromPrice;
    }

    public void setFromPrice(int fromPrice) {
	this.fromPrice = fromPrice;
    }

    public int getToPrice() {
	return toPrice;
    }

    public void setToPrice(int toPrice) {
	this.toPrice = toPrice;
    }
}
