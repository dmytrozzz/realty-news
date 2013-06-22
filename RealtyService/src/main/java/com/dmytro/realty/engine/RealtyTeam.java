package com.dmytro.realty.engine;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.engine.builder.*;
import com.dmytro.realty.engine.parser.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class RealtyTeam {

    private ARealtyRequestBuilder criteriaConverter;
    private IRealtyParser realtyParser;

    private Map<Long, LinkedList<String>> criteriaMap = new HashMap<>();

    private RealtyTeam(ARealtyRequestBuilder converter, IRealtyParser parser) {
        this.criteriaConverter = converter;
        this.realtyParser = parser;
        this.realtyParser.setRequestBuilder(converter);
    }

    public List<RealtyOffer> collectOffers(RealtyCriteria criteria) throws RealtyUnparsebleException {
        if (!criteriaMap.containsKey(criteria.getId()))
            criteriaMap.put(criteria.getId(), new LinkedList<String>());

        String request = criteriaConverter.buildRequest(criteria);

        System.out.println(request);

        return grabRealtyOffers(request, criteria.getId());
    }

    /**
     * Grabs list of offer links from site page
     *
     * @param request    request to site with criteria parameters
     * @param criteriaId criteria id
     * @return List of new realty offers from page
     */
    @SuppressWarnings("unchecked")
    private List<RealtyOffer> grabRealtyOffers(String request, long criteriaId) throws RealtyUnparsebleException {

        List<RealtyOffer> newOffers = new LinkedList<>();

        List<String> oldLinks = criteriaMap.get(criteriaId);
        List<String> parsedLinks = null;
        Collection<String> newLinks = null;

        parsedLinks = realtyParser.parseRequest(request);

        newLinks = CollectionUtils.disjunction(oldLinks, CollectionUtils.union(oldLinks, parsedLinks));


        if (parsedLinks != null && newLinks != null && newLinks.size() > 0) {
            oldLinks.clear();
            oldLinks.addAll(parsedLinks);
            for (String newOfferLink : newLinks) {
                try {
                    newOffers.add(realtyParser.parseOffer(newOfferLink));
                } catch (RealtyUnparsebleException rue) {
                    rue.printStackTrace();
                }
            }
        }
        return newOffers;
    }

    public static List<RealtyTeam> createTeams() {
        List<RealtyTeam> realtyTeams = new LinkedList<>();
        // Slando
        realtyTeams.add(new RealtyTeam(new SlandoRequestBuilder(), new SlandoRealtyParser()));

        // Aviso
        realtyTeams.add(new RealtyTeam(new AvisoRequestBuilder(), new AvisoRealtyParser()));

        // Rio
        realtyTeams.add(new RealtyTeam(new RioRequestBuilder(), new RioRealtyParser()));

        // Realtor
        realtyTeams.add(new RealtyTeam(new RealtorRequestBuilder(), new RealtorRealtyParser()));

        // MirKvartir
        realtyTeams.add(new RealtyTeam(new MirKvartirRequestBuilder(), new MirKvartirRealtyParser()));
        return realtyTeams;
    }
}
