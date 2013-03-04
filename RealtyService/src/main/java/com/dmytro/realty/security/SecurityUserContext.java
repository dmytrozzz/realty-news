package com.dmytro.realty.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dmytro.realty.domain.RealtyUser;

public class SecurityUserContext implements UserContext {

    @Override
    public RealtyUser getCurrentUser() {
	SecurityContext context = SecurityContextHolder.getContext();
	Authentication authentication = context.getAuthentication();
	if (authentication == null)
	    return null;
	return (RealtyUser) authentication.getPrincipal();
    }

    @Override
    public void setCurrentUser(RealtyUser user) {
	Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
	Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
	SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
