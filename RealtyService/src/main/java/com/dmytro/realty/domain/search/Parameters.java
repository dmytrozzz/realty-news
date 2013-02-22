package com.dmytro.realty.domain.search;

import java.io.Serializable;

public class Parameters implements Serializable {
    private int fromPrice = 1000;
    private int toPrice = 5000;

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
