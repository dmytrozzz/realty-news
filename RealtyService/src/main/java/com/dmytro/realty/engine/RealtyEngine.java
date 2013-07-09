package com.dmytro.realty.engine;

import com.dmytro.realty.domain.Product;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyOffer;
import com.dmytro.realty.engine.parser.RealtyUnparsebleException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RealtyEngine {
    private List<RealtyTeam> realtyTeams = new LinkedList<>();

    public RealtyEngine() {
        realtyTeams.addAll(RealtyTeam.createTeams());

    }

    private static RealtyCriteria criteria(Product.Type type, int fromP, int toP, int fromR, int toR, Product.Operation oper) {
        RealtyCriteria realtyCriteria = new RealtyCriteria();
        realtyCriteria.setProductType(type);
        realtyCriteria.getParameters().setFromPrice(fromP);
        realtyCriteria.getParameters().setToPrice(toP);

        realtyCriteria.getParameters().setFromRooms(fromR);
        realtyCriteria.getParameters().setToRooms(toR);

        realtyCriteria.setOperation(oper);
        return realtyCriteria;
    }

    /**
     * Runs mechanism of searching new offers and sending it to users
     *
     * @param criteria {@link RealtyCriteria} for which users subscribed
     */
    public List<RealtyOffer> searchByCriteria(RealtyCriteria criteria) throws RealtyUnparsebleException {
        List<RealtyOffer> resultOffers = new ArrayList<>();

        for (RealtyTeam team : realtyTeams) {
            try {
                resultOffers.addAll(team.collectOffers(criteria));
            } catch (RealtyUnparsebleException rue) {
                rue.printStackTrace();
            }
        }

        return resultOffers;
    }
}
