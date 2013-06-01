package com.dmytro.realty.service;

import com.dmytro.realty.domain.Billing;
import com.dmytro.realty.service.moneymaker.LiqPayRequest;

public interface IPayService {
	
	LiqPayRequest createLiqPayBilling();

    void processLiqPayBilling(String xml);

    void processEasyPayBilling(String orderId, int amount, String comission);

	Billing createEasyPayBilling();
}
