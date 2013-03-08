package com.dmytro.realty.logic.engine;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.logic.engine.builder.SlandoCriteriaConverter;

public class RealtyEngine {
    private SendMan sendMan;
    private RealtyParser realtyParser;

    public RealtyEngine() {
	sendMan = new SendMan();
	realtyParser = new RealtyParser();
    }

    private List<RealtyUnit> grabRealtyUnits(String address) {
	List<RealtyUnit> realtyUnits = new LinkedList<>();
	List<String> offerLinks = realtyParser.parseOffersList(address);
	for (String link : offerLinks)
	    realtyUnits.add(realtyParser.parseOffer(link));

	return realtyUnits;
    }

    private void sendNews(List<RealtyUnit> realtyUnits, String to) {
	String content = "";
	for (RealtyUnit unit : realtyUnits)
	    content += unit.toString();
	if (content.length() > 1)
	    sendMan.sendNews(content, to);
    }

    public void searchAndSubscribe(RealtyCriteria criteria, Collection<RealtyUser> userCollection) {
	String request = new SlandoCriteriaConverter().buildRequest(criteria);
	List<RealtyUnit> realtyUnits = grabRealtyUnits(request);
	for (RealtyUser user : userCollection)
	    sendNews(realtyUnits, user.getEmail());
    }
}
