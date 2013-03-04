package com.dmytro.realty.service;

import com.dmytro.realty.domain.RealtyUser;

public interface IUserService {
    public void addUser(RealtyUser user);
    
    public RealtyUser findUserByLogin(String login);
}
