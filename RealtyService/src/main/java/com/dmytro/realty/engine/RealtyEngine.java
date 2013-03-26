package com.dmytro.realty.engine;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

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

	private Map<Long, LinkedList<String>> criteriaMap = new HashMap<>();

	public RealtyEngine() {
		sendMan = new SendMan();
		realtyParser = new SlandoRealtyParser();
		criteriaConverter = new SlandoCriteriaConverter();
	}

	/**
	 * Grabs list of offer links from site page
	 * 
	 * @param request
	 *            request to site with criteria parameters
	 * @param criteriaId
	 *            criteria id
	 * @return List of new realty offers from page
	 */
	@SuppressWarnings("unchecked")
	private List<RealtyOffer> grabRealtyOffers(String request, long criteriaId) {
		List<RealtyOffer> newOffers = new LinkedList<>();

		List<String> oldLinks = criteriaMap.get(criteriaId);
		List<String> parsedLinks = null;
		Collection<String> newLinks = null;

		try {
			parsedLinks = realtyParser.parseRequest(request);
			newLinks = CollectionUtils.disjunction(oldLinks,
					CollectionUtils.union(oldLinks, parsedLinks));
		} catch (RealtyUnparsebleException e) {
			e.printStackTrace();
		}

		if (parsedLinks != null && newLinks != null && newLinks.size() > 0) {
			oldLinks.clear();
			oldLinks.addAll(parsedLinks);
			for (String newOfferLink : newLinks) {
				try {
					newOffers.add(realtyParser.parseOffer(newOfferLink));
				} catch (RealtyUnparsebleException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		return newOffers;
	}

	/**
	 * Sends new offers to users
	 * 
	 * @param newRealtyOffers
	 *            list of new realty offers bigger then 0
	 * @param realtyUsers
	 *            list of users, subscribed for this criteria
	 */
	private void sendNews(List<RealtyOffer> newRealtyOffers, Collection<RealtyUser> realtyUsers) {
		sendMan.createMessage(newRealtyOffers);
		// TODO from DB already List or array of emails
		for (RealtyUser user : realtyUsers) {
			sendMan.addRecipient(user.getEmail());
		}
		sendMan.sendEmail();
	}

	/**
	 * Runs mechanism of searching new offers and sending it to users
	 * 
	 * @param criteria
	 *            {@link RealtyCriteria} for which users subscribed
	 * @param userCollection
	 *            collection of {@link RealtyUser}, subscribed for criteria
	 */
	public void searchAndSubscribe(RealtyCriteria criteria, Collection<RealtyUser> userCollection) {
		if (!criteriaMap.containsKey(criteria.getId()))
			criteriaMap.put(criteria.getId(), new LinkedList<String>());

		String request = criteriaConverter.buildRequest(criteria);

		List<RealtyOffer> realtyOffers = grabRealtyOffers(request, criteria.getId());
		
		if (realtyOffers.size() > 0)
			sendNews(realtyOffers, userCollection);
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
