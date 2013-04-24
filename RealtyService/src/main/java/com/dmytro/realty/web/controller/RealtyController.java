package com.dmytro.realty.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.security.RealtyUserDetails;
import com.dmytro.realty.service.IUserService;
import com.dmytro.realty.web.flow.jsf.BlogBean;
import com.dmytro.realty.web.flow.jsf.FeedBackBeen;
import com.dmytro.realty.web.flow.jsf.PersonalCabinetBean;
import com.dmytro.realty.web.flow.jsf.RealtyWizard;
import com.dmytro.realty.web.flow.jsf.UserPreferencesBean;
import com.dmytro.realty.web.flow.jsf.buffer.CriteriaBean;

@Controller("realtyController")
public class RealtyController {

	private final static Collection<? extends GrantedAuthority> USER_AUTHORITY = AuthorityUtils
			.createAuthorityList("ROLE_USER");

	@Autowired
	private IUserService userService;

	public UserPreferencesBean getPreferences() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getAuthorities().containsAll(USER_AUTHORITY)) {
			RealtyUser realtyUser = null;
			if (authentication.getPrincipal() instanceof RealtyUserDetails)
				realtyUser = ((RealtyUserDetails) authentication.getPrincipal()).getRealtyUser();
			else if (authentication.getPrincipal() instanceof RealtyUser)
				realtyUser = (RealtyUser) authentication.getPrincipal();
			else {
				return new UserPreferencesBean(new RealtyUser());
			}
			System.out.println("Welome, " + realtyUser);
			return new UserPreferencesBean(realtyUser);
		}
		return new UserPreferencesBean();
	}

	public RealtyWizard getWizard(UserPreferencesBean preferences) {
		return new RealtyWizard(preferences);
	}

	public PersonalCabinetBean getCabinet(UserPreferencesBean preferences) {
		return new PersonalCabinetBean(preferences);
	}	

	public BlogBean getBlogBean() {
		// TODO
		return new BlogBean(null);
	}

	public boolean isAuthorized() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().containsAll(USER_AUTHORITY);
	}

	public void saveUser(UserPreferencesBean preferencesBean) {
		authorizeUser(preferencesBean.getUser());

		RealtyUser user = preferencesBean.getUser();
		user.getCriteriaCollection().clear();
		for (CriteriaBean criteriaBean : preferencesBean.getCriteriaList()) {
			user.getCriteriaCollection().add(criteriaBean.getRealtyCriteria());
		}
		userService.saveUser(user);
	}

	private void authorizeUser(RealtyUser user) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
				USER_AUTHORITY);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public void runService(RealtyUser user) {
		user.setEnabled(!user.isEnabled());
	}

	public void payForService(RealtyUser user) {
		user.setPayed(true);
		user.setEnabled(true);
	}
}
