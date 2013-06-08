package com.dmytro.realty.engine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.dmytro.realty.engine.builder.MirKvartirRequestBuilder;
import com.dmytro.realty.engine.parser.*;
import org.apache.commons.collections.CollectionUtils;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.engine.builder.DefaultRealtyRequestBuilder;
import com.dmytro.realty.engine.builder.AvisoRequestBuilder;
import com.dmytro.realty.engine.builder.RealtorRequestBuilder;

public class RealtyTeam {

	private DefaultRealtyRequestBuilder criteriaConverter;
	private IRealtyParser realtyParser;

	private Map<Long, LinkedList<String>> criteriaMap = new HashMap<>();

	private RealtyTeam(String teamName, DefaultRealtyRequestBuilder converter, IRealtyParser parser) {
		Properties properties = getProperties(teamName);
		this.criteriaConverter = converter;
		this.criteriaConverter.setProperties(properties);
		this.realtyParser = parser;
		this.realtyParser.setRequestBuilder(converter);
	}

	public List<RealtyOffer> collectOffers(RealtyCriteria criteria) {
		if (!criteriaMap.containsKey(criteria.getId()))
			criteriaMap.put(criteria.getId(), new LinkedList<String>());

		String request = criteriaConverter.buildRequest(criteria);

		System.out.println(request);

		return grabRealtyOffers(request, criteria.getId());
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
			newLinks = CollectionUtils.disjunction(oldLinks, CollectionUtils.union(oldLinks, parsedLinks));
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

	private Properties getProperties(String teamName) {
		Properties properties = new Properties();
		try (InputStream propFile = new FileInputStream(teamName + ".properties")) {
			properties.load(propFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static List<RealtyTeam> createTeams() {
		List<RealtyTeam> realtyTeams = new LinkedList<>();
		// Slando
		realtyTeams.add(new RealtyTeam("slando", new DefaultRealtyRequestBuilder(), new SlandoRealtyParser()));

		// Aviso
		// realtyTeams.add(new RealtyTeam("aviso", new AvisoRequestBuilder(),
		// new AvisoRealtyParser()));

		// Rio
		// realtyTeams.add(new RealtyTeam("rio", new
		// DefaultRealtyRequestBuilder(), new RioRealtyParser()));

		// Realtor
		// realtyTeams.add(new RealtyTeam("rieltor", new
		// RealtorRequestBuilder(), new RealtorRealtyParser()));

        // MirKvartir
        // realtyTeams.add(new RealtyTeam("mir-kvartir", new
        //         MirKvartirRequestBuilder(), new MirKvartirRealtyParser()));
		return realtyTeams;
	}
}
