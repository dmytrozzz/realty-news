package com.dmytro.realty.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.dmytro.realty.domain.RealtyUser;

public class RealtyUserDetails implements UserDetails {
    private RealtyUser realtyUser;

    public RealtyUserDetails(RealtyUser user) {
	realtyUser = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public String getUsername() {
	return realtyUser.getLogin();
    }

    @Override
    public String getPassword() {
	return realtyUser.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    public RealtyUser getRealtyUser() { 
	return realtyUser;
    }
}
