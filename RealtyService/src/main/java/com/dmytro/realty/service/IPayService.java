package com.dmytro.realty.service;

import com.dmytro.realty.service.moneymaker.LiqPayRequest;

public interface IPayService {
	
	LiqPayRequest createLiqPayBilling();

    void processLiqPayBilling(String xml);

}
