package com.dmytro.realty.logic.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyParameters;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;
import com.dmytro.realty.logic.engine.builder.RealtyCriteriaConverter;
import com.dmytro.realty.logic.engine.builder.SlandoCriteriaConverter;
import com.dmytro.realty.logic.engine.parser.IRealtyParser;
import com.dmytro.realty.logic.engine.parser.RealtyUnparsebleException;
import com.dmytro.realty.logic.engine.parser.SlandoRealtyParser;

public class RealtyEngine {
    private SendMan sendMan;
    private IRealtyParser realtyParser;
    private RealtyCriteriaConverter criteriaConverter;
    private Map<Long, List<String>> criteriaMap = new HashMap<>();

    public RealtyEngine() {
	sendMan = new SendMan();
	realtyParser = new SlandoRealtyParser();
	criteriaConverter = new SlandoCriteriaConverter();
    }

    private List<RealtyUnit> grabRealtyUnits(String request) {
	List<RealtyUnit> realtyUnits = new LinkedList<>();
	List<String> offerLinks = new ArrayList<>();
	try {
	    offerLinks = realtyParser.parseRequest(request);
	} catch (Exception e) {
	}

	for (String link : offerLinks) {
	    try {
		realtyUnits.add(realtyParser.parseOffer(link));
	    } catch (Exception e) {
		continue;
	    }
	}

	return realtyUnits;
    }

    private void sendNews(List<RealtyUnit> realtyUnits, String to) {
	String content = "";
	for (RealtyUnit unit : realtyUnits)
	    content += unit.toString();
	if (content.length() > 1)
	    sendMan.sendNews(to, content);
    }

    public void searchAndSubscribe(RealtyCriteria criteria, Collection<RealtyUser> userCollection) {
	String request = criteriaConverter.buildRequest(criteria);	

	List<RealtyUnit> realtyUnits = grabRealtyUnits(request);		
	
	for (RealtyUser user : userCollection) {	    
	    sendNews(realtyUnits, user.getEmail());
	}
    }

    public static void main(String[] args) {
	RealtyCriteria realtyCriteria = new RealtyCriteria();
	realtyCriteria.setProductType(ProductType.ROOM);
	realtyCriteria.getParameters().setFromPrice(2000);
	realtyCriteria.getParameters().setToPrice(4000);

	realtyCriteria.setOperations(Collections.singleton(OperationType.RENT.name()));

	System.out.println("CRITERIA: -->" + realtyCriteria);

	RealtyUser user = new RealtyUser();
	user.setEmail("d.zonov@ukr.net");

	RealtyEngine engine = new RealtyEngine();
	engine.searchAndSubscribe(realtyCriteria, Collections.singletonList(user));

    }
}
