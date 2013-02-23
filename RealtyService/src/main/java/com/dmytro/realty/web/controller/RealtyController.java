package com.dmytro.realty.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dmytro.realty.domain.User;
import com.dmytro.realty.service.INewsFeedService;
import com.dmytro.realty.service.IUserService;
import com.dmytro.realty.web.flow.jsf.PersonalCabinetBean;
import com.dmytro.realty.web.flow.jsf.RealtyWizard;
import com.dmytro.realty.web.flow.jsf.UserPreferencesBean;

@Controller("realtyController")
public class RealtyController {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private INewsFeedService newsFeedService;

    public RealtyWizard getWizard() {
	return new RealtyWizard();
    }
    
    public PersonalCabinetBean getCabinet() {
	return new PersonalCabinetBean();
    }

    public void saveUser(User user) {
	//userService.addUser(user);
    }

    public void saveNewsFeed(UserPreferencesBean preferencesBean) {
	//TODO 1. Find news feed with given parameters.
	//2. if exists - add new user.
	//3. Otherwise - create and save;
	//4. Maybe this all is possible with help of spring-data
	
	//NewsFeed newsFeed = new NewsFeed();
	//newsFeed.setUser(preferencesBean.getUser());
	//newsFeedService.addNewsFeed(newsFeed);
    }
}
