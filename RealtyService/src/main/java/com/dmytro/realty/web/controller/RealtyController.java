package com.dmytro.realty.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.service.IUserService;
import com.dmytro.realty.web.flow.jsf.PersonalCabinetBean;
import com.dmytro.realty.web.flow.jsf.RealtyWizard;
import com.dmytro.realty.web.flow.jsf.UserPreferencesBean;

@Controller("realtyController")
public class RealtyController {

    @Autowired
    private IUserService userService;

    public RealtyWizard getWizard(UserPreferencesBean preferences) {
	return new RealtyWizard(preferences);
    }

    public PersonalCabinetBean getCabinet(UserPreferencesBean preferences) {
	return new PersonalCabinetBean(preferences);
    }

    public void saveUser(UserPreferencesBean preferencesBean) {
	RealtyUser user = preferencesBean.getUser();
	user.setCriteriaCollection(preferencesBean.getCriteriaList());
	userService.saveUser(user);
    }
}
