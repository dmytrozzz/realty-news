package com.dmytro.realty.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "billing")
public class Billing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_seq")
    @SequenceGenerator(name = "billing_seq", sequenceName = "billing_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    private int sum;
    
    @Enumerated(EnumType.STRING)
    private BillingState status;
    
    @Enumerated(EnumType.STRING)
    private BillingService service;

    private String uniqueID;
    
    private String transactionID;

    @OneToOne(mappedBy = "billing")
    private RealtyUser user;

    public enum BillingState {
        PROCESSING, PAYED, FAILED, NEW
    }

    public enum BillingService {
        LIQPAY, EASYPAY, SPRYPAY
    }
    
    public Billing(){
    	
    }

    public Billing(int sum, BillingState status, BillingService service) {
        this.sum = sum;
        this.status = status;
        this.service = service;
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
    
    public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public RealtyUser getUser() {
        return user;
    }

    public void setUser(RealtyUser user) {
        this.user = user;
    }

    public static Billing createLiqPayBilling() {
        return new Billing(1, BillingState.NEW, BillingService.LIQPAY);
    }

    public static Billing createEasyPayBilling() {
        return new Billing(1, BillingState.NEW, BillingService.EASYPAY);
    }

    public static Billing createSpryPayBilling() {
        return new Billing(1, BillingState.NEW, BillingService.SPRYPAY);
    }


}
