package com.dmytro.realty.service;

import com.dmytro.realty.domain.RealtyOffer;
import com.dmytro.realty.domain.RealtyUser;

import java.util.List;

public interface ISendManService {

    public void sendNews(List<RealtyOffer> newRealtyOffers,
                         String[] emails);

    public void sendHTMLMessage(String html, RealtyUser user);
}
