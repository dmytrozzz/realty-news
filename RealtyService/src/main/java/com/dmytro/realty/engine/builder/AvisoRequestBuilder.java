package com.dmytro.realty.engine.builder;

import com.dmytro.realty.domain.Product;
import com.dmytro.realty.domain.RealtyCriteria;

public class AvisoRequestBuilder extends ARealtyRequestBuilder {

    @Override
    public String host() {
        return "http://www.aviso.ua/kiev";
    }

    @Override
    protected String criteria(RealtyCriteria criteria) {
        String criteriaString = "/list.php?r=";
        switch (criteria.getProductType()) {
            case APARTMENT:
                if (criteria.getOperation() == Product.Operation.BUY)
                    return criteriaString + "101";
                else if (criteria.getOperation() == Product.Operation.RENT)
                    return criteriaString + "121";
            case ROOM:
                return criteriaString + "126";
//            case HOUSE:
//                if (criteria.getOperation() == Product.Operation.BUY)
//                    return criteriaString + "191";
//                else if (criteria.getOperation() == Product.Operation.RENT)
//                    return criteriaString + "196";
        }
        return "";
    }

    @Override
    protected String location(Product.Location location) {
        if (location == Product.Location.ALL)
            return "";
        return "&distr=" + location.getAvisoIndex();
    }

    @Override
    protected String price(int from, int to) {
        return "&" + parametrize("pricefrom={0}&priceto={1}", from, to) + "&curr=3";
    }

    @Override
    protected String rooms(int fromRooms, int toRooms) {
        String rooms = "";
        for (int i = fromRooms; i < toRooms; i++) {
            rooms += "&" + parametrize("room%5B{0}%5D={0}", i);
        }
        return rooms;
    }
}
