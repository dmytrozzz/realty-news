package com.dmytro.realty.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dmytro.realty.domain.NewsFeed;
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

    public RealtyWizard getWizard(UserPreferencesBean preferences) {
	return new RealtyWizard(preferences);
    }

    public PersonalCabinetBean getCabinet(UserPreferencesBean preferences) {
	return new PersonalCabinetBean(preferences);
    }

    public void saveNewsFeed(UserPreferencesBean preferencesBean) {
	userService.addUser(preferencesBean.getUser());

	// List<NewsFeed> newsFeeds = new LinkedList<>();
	// for(SearchCriteria criteria : preferencesBean.getCriteriaList()){
	// newsFeeds.add(new NewsFeed(preferencesBean.getUser(), criteria));
	// }
	// newsFeedService.addNewsFeeds(newsFeeds);
    }

    public void getNewsFeed() {
	NewsFeed newsFeed = newsFeedService.getNewsFeed(5l);
	System.out.print(newsFeed.getUserCollection());
	System.out.println(newsFeed.getCriteria());
    }
}
