package com.dmytro.realty.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.mail.EmailException;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyUser;
import com.dmytro.realty.domain.search.enums.OperationType;
import com.dmytro.realty.domain.search.enums.ProductType;
import com.dmytro.realty.engine.builder.RealtyCriteriaConverter;
import com.dmytro.realty.engine.builder.SlandoCriteriaConverter;
import com.dmytro.realty.engine.parser.IRealtyParser;
import com.dmytro.realty.engine.parser.RealtyUnparsebleException;
import com.dmytro.realty.engine.parser.SlandoRealtyParser;

public class RealtyEngine {
    private SendMan sendMan;
    private IRealtyParser realtyParser;
    private RealtyCriteriaConverter criteriaConverter;

    private Map<Long, LinkedSet> criteriaMap = new HashMap<>();

    public RealtyEngine() {
	sendMan = new SendMan();
	realtyParser = new SlandoRealtyParser();
	criteriaConverter = new SlandoCriteriaConverter();
    }

    private List<RealtyOffer> grabRealtyOffers(String request, long criteriaId) {
	List<RealtyOffer> resultOfferList = new LinkedList<>();

	try {
	    List<String> offersLinks = realtyParser.parseRequest(request);	    
	    for (String offer : offersLinks) {
		
		if (criteriaMap.get(criteriaId).add(offer)) {
		    try {
			resultOfferList.add(realtyParser.parseOffer(offer));
		    } catch (RealtyUnparsebleException e) {
			e.printStackTrace();
			continue;
		    }
		}

	    }
	} catch (RealtyUnparsebleException e) {
	    e.printStackTrace();
	}

	return resultOfferList;
    }

    private void sendNews(List<RealtyOffer> realtyUnits, Collection<RealtyUser> realtyUsers) {
	if (realtyUnits.size() > 0) {
	    sendMan.createMessage(realtyUnits);
	    // TODO from DB already List or array of emails
	    for (RealtyUser user : realtyUsers) {
		sendMan.addRecipient(user.getEmail());
	    }
	    sendMan.sendEmail();
	}
    }

    public void searchAndSubscribe(RealtyCriteria criteria, Collection<RealtyUser> userCollection) {
	if (!criteriaMap.containsKey(criteria.getId()))
	    criteriaMap.put(criteria.getId(), new LinkedSet());

	String request = criteriaConverter.buildRequest(criteria);

	List<RealtyOffer> realtyOffers = grabRealtyOffers(request, criteria.getId());	

	sendNews(realtyOffers, userCollection);
    }

    private static class LinkedSet {
	private SetUniqueList criteriaLinkedSet = SetUniqueList.decorate(new ArrayList<String>());

	public boolean add(Object element) {
	    System.out.println(element);
	    if (!criteriaLinkedSet.contains(element)) {
		System.out.println("In cycle: " + element);
		removeLast();
		criteriaLinkedSet.add(0, element);
		return true;
	    }
	    return false;
	}

	private void removeLast() {
	    if (criteriaLinkedSet.size() > 0)
		criteriaLinkedSet.remove(criteriaLinkedSet.size() - 1);
	}
    }

    public static void main(String[] args) throws InterruptedException {		
	RealtyCriteria realtyCriteria = new RealtyCriteria();
	realtyCriteria.setProductType(ProductType.APPARTMENT);
	realtyCriteria.getParameters().setFromPrice(2000);
	realtyCriteria.getParameters().setToPrice(4000);

	realtyCriteria.setOperations(Collections.singleton(OperationType.RENT.name()));

	RealtyUser user = new RealtyUser();
	user.setEmail("d.zonov@ukr.net");

	RealtyEngine engine = new RealtyEngine();
	while (true) {
	    System.out.println("Lets go!");
	    engine.searchAndSubscribe(realtyCriteria, Collections.singletonList(user));
	    Thread.sleep(20000);
	}

    }
}
