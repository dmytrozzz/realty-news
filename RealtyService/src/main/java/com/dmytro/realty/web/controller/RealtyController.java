package com.dmytro.realty.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.service.IUserService;
import com.dmytro.realty.web.flow.jsf.PersonalCabinetBean;
import com.dmytro.realty.web.flow.jsf.RealtyWizard;
import com.dmytro.realty.web.flow.jsf.UserPreferencesBean;

@Controller("realtyController")
public class RealtyController {
    private final static Collection<? extends GrantedAuthority> USER_AUTHORITY = AuthorityUtils
	    .createAuthorityList("ROLE_USER");

    @Autowired
    private IUserService userService;

    public UserPreferencesBean getPreferences() {
	UserPreferencesBean preferencesBean = new UserPreferencesBean();
	preferencesBean.setUser(getCurrentUser());
	preferencesBean.setCriteriaList(preferencesBean.getUser().getCriteriaCollection());
	return preferencesBean;
    }

    public RealtyWizard getWizard(UserPreferencesBean preferences) {
	return new RealtyWizard(preferences);
    }

    public PersonalCabinetBean getCabinet(UserPreferencesBean preferences) {
	return new PersonalCabinetBean(preferences);
    }

    public boolean isAuthorized() {
	return SecurityContextHolder.getContext().getAuthentication().getAuthorities().containsAll(USER_AUTHORITY);
    }

    public void saveUser(UserPreferencesBean preferencesBean) {
	authorizeUser(preferencesBean.getUser());

	RealtyUser user = preferencesBean.getUser();
	user.setCriteriaCollection(preferencesBean.getCriteriaList());
	userService.saveUser(user);
    }

    private void authorizeUser(RealtyUser user) {
	Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
		USER_AUTHORITY);
	SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private RealtyUser getCurrentUser() {
	SecurityContext context = SecurityContextHolder.getContext();
	Authentication authentication = context.getAuthentication();
	if (authentication == null)
	    return null;
	return (RealtyUser) authentication.getPrincipal();
    }
}
