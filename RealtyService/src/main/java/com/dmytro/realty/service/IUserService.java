package com.dmytro.realty.service;

import com.dmytro.realty.domain.RealtyUser;

public interface IUserService {
    public RealtyUser findUserByLogin(String login);

    public void saveUser(RealtyUser user);

    public void registration(RealtyUser user);

    public RealtyUser getCurrentUser();

    public void update(RealtyUser user);
}
