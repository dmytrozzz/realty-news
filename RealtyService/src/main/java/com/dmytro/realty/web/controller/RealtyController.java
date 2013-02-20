package com.dmytro.realty.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dmytro.realty.domain.NewsFeed;
import com.dmytro.realty.domain.User;
import com.dmytro.realty.service.INewsFeedService;
import com.dmytro.realty.service.IUserService;
import com.dmytro.realty.web.flow.jsf.RealtyWizard;

@Controller("realtyController")
public class RealtyController {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private INewsFeedService newsFeedService;

    public RealtyWizard getWizard() {
	return new RealtyWizard();
    }

    public void addUser(User user) {
	userService.addUser(user);
    }

    public void addNewsFeed(RealtyWizard realtyWizard) {
	NewsFeed newsFeed = new NewsFeed();
	newsFeed.setUser(realtyWizard.getUser());
	newsFeedService.addNewsFeed(newsFeed);
    }
}
