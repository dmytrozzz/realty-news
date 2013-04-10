package com.dmytro.realty.web.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.view.RedirectView;

@Controller("payMaBillController")
public class PayMaBillController {

	@RequestMapping(value = "/user-pay/easy/success", method = RequestMethod.GET)
	public void paySuccess(@RequestParam(value = "order_id") long orderId, @RequestParam(value = "amount") int amount,
			@RequestParam(value = "commission") String commision) {
		System.out.println("Pay was made: " + orderId + "|" + amount + "|" + commision + "|");
		
		
	}

	public RedirectView payError() {
		return new RedirectView("");
	}
}
