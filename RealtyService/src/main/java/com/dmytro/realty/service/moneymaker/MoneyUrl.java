package com.dmytro.realty.service.moneymaker;

public interface MoneyUrl {
	String BASE_URL = "/user-pay/";
	String LIQ_PAY_REQUEST = BASE_URL + "liq/pay";
	String LIQ_PAY_RESPONSE_HANDLER = BASE_URL + "liq/process";
	
}
