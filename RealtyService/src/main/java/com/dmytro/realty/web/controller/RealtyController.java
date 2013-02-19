package com.dmytro.realty.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dmytro.realty.domain.User;
import com.dmytro.realty.service.IUserService;
import com.dmytro.realty.web.flow.jsf.RealtyWizard;

@Controller("realtyController")
public class RealtyController {

    @Autowired
    private IUserService userService;

    public RealtyWizard getWizard() {
	return new RealtyWizard();
    }

    public void addUser(User user) {
	userService.addUser(user);
    }
}
