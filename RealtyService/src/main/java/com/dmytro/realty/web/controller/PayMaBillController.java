package com.dmytro.realty.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("payMaBillController")
public class PayMaBillController {

    @RequestMapping(value = "/user-pay/easy/success", method = RequestMethod.GET)
    public void paySuccess(@RequestParam(value = "order_id") long orderId, @RequestParam(value = "amount") int amount,
	    @RequestParam(value = "commission") String commision) {
	System.out.println("Pay was made: " + orderId + "|" + amount + "|" + commision + "|");
    }

    public void payError() {

    }
}
