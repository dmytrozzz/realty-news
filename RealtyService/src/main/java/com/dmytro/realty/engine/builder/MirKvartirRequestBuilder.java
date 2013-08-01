package com.dmytro.realty.engine.builder;

import com.dmytro.realty.domain.Product;
import com.dmytro.realty.domain.RealtyCriteria;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 24.05.13
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public class MirKvartirRequestBuilder extends ARealtyRequestBuilder {

    @Override
    public String host() {
        return "http://mirkvartir.ua";
    }

    @Override
    protected String criteria(RealtyCriteria criteria) {
        String criteriaString = "/?module=offers";
        switch (criteria.getProductType()) {
            case APARTMENT:
                if (criteria.getOperation() == Product.Operation.BUY)
                    criteriaString += "&section=1972";
                else if (criteria.getOperation() == Product.Operation.RENT)
                    criteriaString += "&section=1976";
                break;
            case ROOM:
                criteriaString += "&section=1977";
                break;
//            case HOUSE:
//                return "";
        }
        return criteriaString + "&s=1&region=1";
    }

    @Override
    protected String location(Product.Location location) {
        if(location == Product.Location.ALL)
            return "";
        return "&slist=" + location.getMirKvartirIndex();
    }

    protected String rooms(int fromRooms, int toRooms) {
        String roomProperty = fromRooms >= 5 ? "5-100" : fromRooms + "";
        if (fromRooms != toRooms && fromRooms < 5) {
            for (int i = fromRooms + 1; i <= toRooms; i++) {
                if (toRooms < 5)
                    roomProperty += "%2C" + i;
                else
                    roomProperty += "%2C5-100";
            }
        }

        return "&" + parametrize("flat={0}", roomProperty);
    }

    @Override
    protected String price(int fromPrice, int toPrice) {
        return "&price=от+" + fromPrice + "+до+" + toPrice + "&currency=2";
    }
}
