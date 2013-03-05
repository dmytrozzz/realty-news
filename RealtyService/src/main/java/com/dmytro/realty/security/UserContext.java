package com.dmytro.realty.security;

import com.dmytro.realty.domain.RealtyUser;

public interface UserContext {
    RealtyUser getCurrentUser();

    void setCurrentUser(RealtyUser user);

    boolean isAuthenticated();
}
