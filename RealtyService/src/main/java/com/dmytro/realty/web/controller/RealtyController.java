package com.dmytro.realty.web.controller;

import org.springframework.stereotype.Controller;

import com.dmytro.realty.web.flow.jsf.RealtyWizard;

@Controller("realtyController")
public class RealtyController {

	public RealtyWizard getWizard() {
		return new RealtyWizard();
	}
}
