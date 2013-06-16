package com.dmytro.realty.engine.builder;

import com.dmytro.realty.domain.Product;
import com.dmytro.realty.domain.RealtyCriteria;

public class RealtorRequestBuilder extends ARealtyRequestBuilder {

    @Override
    public String host() {
        return "http://rieltor.ua";
    }

    @Override
    protected String criteria(RealtyCriteria criteria) {
        String criteriaString = "/";
        switch (criteria.getProductType()) {
            case ROOM:
                criteriaString += "rooms";
                break;
            case APARTMENT:
                criteriaString += "flats";
                break;
//            case HOUSE:
//                criteriaString += "houses";
//                break;
        }
        criteriaString += "-";
        switch (criteria.getOperation()) {
            case BUY:
                criteriaString += "sale";
                break;
            case RENT:
                criteriaString += "rent";
                break;
        }
        return criteriaString + "/?";
    }

    @Override
    protected String location(Product.Location location) {
        return "&district="+location.getRieltorIndex();
    }

    @Override
    protected String price(int from, int to) {
        //1 - UAH, 2 - USD
        return parametrize("&price-from={0}&price-to={1}&cur-type=1", from, to);
    }

    @Override
    protected String rooms(int fromRooms, int toRooms) {
        String rooms = "";
        for (int i = fromRooms; i <= toRooms - 1; i++) {
            rooms += i + ",";
        }
        rooms += toRooms;
        return parametrize("&rooms={0}", rooms);
    }

    protected String end() {
        return "&sort=bycreated&direction=desc";
    }
}
