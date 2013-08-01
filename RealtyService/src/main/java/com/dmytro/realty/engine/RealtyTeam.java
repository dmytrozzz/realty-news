package com.dmytro.realty.engine;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyOffer;
import com.dmytro.realty.engine.builder.*;
import com.dmytro.realty.engine.parser.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class RealtyTeam {

    private ARealtyRequestBuilder criteriaConverter;
    private IRealtyParser realtyParser;

    private Map<Long, LinkedList<String>> criteriaMap = new HashMap<>();

    public RealtyTeam(ARealtyRequestBuilder converter, IRealtyParser parser) {
        this.criteriaConverter = converter;
        this.realtyParser = parser;
        this.realtyParser.setRequestBuilder(converter);
    }

    public List<RealtyOffer> collectOffers(RealtyCriteria criteria) throws RealtyUnparsebleException {
        if (!criteriaMap.containsKey(criteria.getId()))
            criteriaMap.put(criteria.getId(), new LinkedList<String>());

        String request = criteriaConverter.buildRequest(criteria);

        System.out.println("Criteria request: " + request);

        return grabRealtyOffers(request, criteria);
    }

    /**
     * Grabs list of offer links from site page
     *
     * @param request    request to site with criteria parameters
     * @return List of new realty offers from page
     */
    @SuppressWarnings("unchecked")
    private List<RealtyOffer> grabRealtyOffers(String request, RealtyCriteria criteria) throws RealtyUnparsebleException {
        List<String> oldLinks = criteriaMap.get(criteria.getId());

        List<String> parsedLinks = realtyParser.parseRequest(request);
        System.out.println("=== Found " + parsedLinks.size() + " links! " + parsedLinks);

        Collection<String> newLinks = CollectionUtils.disjunction(oldLinks, CollectionUtils.union(oldLinks, parsedLinks));
        System.out.println("=== Found " + newLinks.size() + " new links! " + newLinks);

        List<RealtyOffer> newOffers = new LinkedList<>();

        if (newLinks.size() > 0) {
            oldLinks.clear();
            oldLinks.addAll(parsedLinks);
            System.out.print("Parsed ... ");
            for (String newOfferLink : newLinks) {
                try {
                    RealtyOffer offer = realtyParser.parseOffer(newOfferLink);
                    System.out.print(offer.getLink() + " ... ");
                    offer.setRealtyCriteria(criteria);
                    newOffers.add(offer);
                } catch (RealtyUnparsebleException rue) {
                    rue.printStackTrace();
                }
            }
        }
        return newOffers;
    }

    @Override
    public String toString() {
        return realtyParser.getClass().getSimpleName();
    }
}
