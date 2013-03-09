package com.dmytro.realty.engine;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;
import com.dmytro.realty.engine.builder.RealtyCriteriaConverter;
import com.dmytro.realty.engine.builder.SlandoCriteriaConverter;
import com.dmytro.realty.engine.parser.IRealtyParser;
import com.dmytro.realty.engine.parser.SlandoRealtyParser;

public class RealtyEngine {
    private SendMan sendMan;
    private IRealtyParser realtyParser;
    private RealtyCriteriaConverter criteriaConverter;
    private Map<Long, Set<String>> criteriaMap = new HashMap<>();

    public RealtyEngine() {
	sendMan = new SendMan();
	realtyParser = new SlandoRealtyParser();
	criteriaConverter = new SlandoCriteriaConverter();
    }

    private List<RealtyUnit> grabRealtyUnits(String request, long criteriaId) {
	List<RealtyUnit> realtyUnits = new LinkedList<>();	
	try {
	    Set<String> source = realtyParser.parseRequest(request);
	    
	    source.removeAll(criteriaMap.get(criteriaId));
	    
	    criteriaMap.get(criteriaId).clear();
	    criteriaMap.get(criteriaId).addAll(source);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	for (String link : criteriaMap.get(criteriaId)) {
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
	if (!criteriaMap.containsKey(criteria.getId()))
	    criteriaMap.put(criteria.getId(), new HashSet<String>());

	String request = criteriaConverter.buildRequest(criteria);

	List<RealtyUnit> realtyUnits = grabRealtyUnits(request, criteria.getId());

	for (RealtyUser user : userCollection) {
	    sendNews(realtyUnits, user.getEmail());
	}
    }

    public static void main(String[] args) throws InterruptedException {
	RealtyCriteria realtyCriteria = new RealtyCriteria();
	realtyCriteria.setProductType(ProductType.ROOM);
	realtyCriteria.getParameters().setFromPrice(2000);
	realtyCriteria.getParameters().setToPrice(4000);

	realtyCriteria.setOperations(Collections.singleton(OperationType.RENT.name()));

	RealtyUser user = new RealtyUser();
	user.setEmail("d.zonov@ukr.net");

	RealtyEngine engine = new RealtyEngine();
	while (true) {
	    System.out.println("Lets go!");
	    engine.searchAndSubscribe(realtyCriteria, Collections.singletonList(user));
	    Thread.sleep(10000);
	}

    }
}
