package com.dmytro.realty.engine;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyOffer;
import com.dmytro.realty.engine.builder.*;
import com.dmytro.realty.engine.parser.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RealtyEngine {
    private List<RealtyTeam> realtyTeams = new LinkedList<>();

    public RealtyEngine() {
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
    }

    /**
     * Runs mechanism of searching new offers and sending it to users
     *
     * @param criteria {@link RealtyCriteria} for which users subscribed
     */
    public List<RealtyOffer> searchByCriteria(RealtyCriteria criteria) throws RealtyUnparsebleException {
        List<RealtyOffer> resultOffers = new ArrayList<>();
        for (RealtyTeam team : realtyTeams) {
            System.out.println("\n\r============================================== Scanning by: " + team + " ==============================================\n\r");
            try {
                List<RealtyOffer> realtyOffers = team.collectOffers(criteria);
                resultOffers.addAll(realtyOffers);
            } catch (RealtyUnparsebleException rue) {
                rue.printStackTrace();
            }
        }

        return resultOffers;
    }
}
