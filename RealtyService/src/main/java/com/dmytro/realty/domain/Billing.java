package com.dmytro.realty.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "billing")
public class Billing implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_seq")
	@SequenceGenerator(name = "billing_seq", sequenceName = "billing_id_seq", allocationSize = 1)
	@Column(name = "id")
	private long id;

	private int sum;
	private BillingState status;
	private BillingService service;
	
	private String uniqueID;
	
	@OneToOne(mappedBy="billing")
	private RealtyUser user;

	public enum BillingState {
		PROCESSING, PAYED, FAILED, NEW
	}

	public enum BillingService {
		LIQPAY, EASYPAY, SPRYPAY
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public BillingState getStatus() {
		return status;
	}

	public void setStatus(BillingState status) {
		this.status = status;
	}

	public BillingService getService() {
		return service;
	}

	public void setService(BillingService service) {
		this.service = service;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public RealtyUser getUser() {
		return user;
	}

	public void setUser(RealtyUser user) {
		this.user = user;
	}	
	
	
}
