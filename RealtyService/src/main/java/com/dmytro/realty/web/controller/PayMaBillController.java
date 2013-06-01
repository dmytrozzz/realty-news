package com.dmytro.realty.web.controller;

import com.dmytro.realty.service.IPayService;
import com.dmytro.realty.service.moneymaker.LiqPayRequest;
import com.dmytro.realty.service.moneymaker.MoneyUrl;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller("payMaBillController")
public class PayMaBillController {
	@Autowired
	private IPayService payService;

	@RequestMapping(value = "/user-pay/easy/success", method = RequestMethod.GET)
	public void easyPaySuccess(@RequestParam(value = "order_id") long orderId,
			@RequestParam(value = "amount") int amount,
			@RequestParam(value = "commission") String commision) {
		System.out.println("Pay was made: " + orderId + "|" + amount + "|"
				+ commision + "|");
	}

	@RequestMapping(value = "/user-pay/easy/error", method = RequestMethod.GET)
	public void easyPayError(@RequestParam(value = "order_id") long orderId) {
		System.out.println("Pay was failed: " + orderId);

	}

	@RequestMapping(value = "/user-pay/easy/pay", method = RequestMethod.GET)
	public RedirectView easyPay() {
		RedirectView redirect = new RedirectView(
				"https://merchant.easypay.ua/client/order");

		redirect.addStaticAttribute("merchant_id",
				"2701a2c2cdfe483ea8ce354dd31991db");
		redirect.addStaticAttribute("desc", "Оплата послуги");
		redirect.addStaticAttribute("amount", 5);
		redirect.addStaticAttribute("order_id", 0);

		return redirect;
	}

	@RequestMapping(value = "/user-pay/liq/pay", method = RequestMethod.GET)
	public RedirectView liqPay() {
		LiqPayRequest request = payService.createLiqPayBilling();
		RedirectView redirect = new RedirectView(
				"https://liqpay.com/?do=clickNbuy");

		// redirect.addStaticAttribute("do", "clickNbuy");
		// redirect.addStaticAttribute("button", "i1032136775");
		redirect.addStaticAttribute("operation_xml", request.getOperationXml());
		redirect.addStaticAttribute("signature", request.getSignature());

		return redirect;
	}

	@RequestMapping(value = MoneyUrl.LIQ_PAY_RESPONSE_HANDLER, method = RequestMethod.POST)
	public void liqPayResponse(@RequestParam("operation_xml") String xmlEncoded) {
		String xml = new String(Base64.decodeBase64(xmlEncoded));
		payService.processLiqPayBilling(xml);
	}

	@RequestMapping(value = "/user-pay/spry/pay", method = RequestMethod.GET)
	public RedirectView spryPay() {
		RedirectView redirect = new RedirectView("http://sprypay.ru/sppi/");

		redirect.addStaticAttribute("spShopId", 212966);
		redirect.addStaticAttribute("spShopPaymentId", 123);
		redirect.addStaticAttribute("spCurrency", "uah");
		redirect.addStaticAttribute("spPurpose", "Subscribing");
		redirect.addStaticAttribute("spAmount", "50.00");
		redirect.addStaticAttribute("spUserDataUserId", 1234567);
		redirect.addStaticAttribute("do", "clickNbuy");
		redirect.addStaticAttribute("button", "i1032136775");
		redirect.addStaticAttribute("do", "clickNbuy");
		redirect.addStaticAttribute("button", "i1032136775");

		return redirect;
	}
}
