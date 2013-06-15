package com.dmytro.realty.engine.builder;

import com.dmytro.realty.domain.Location;
import com.dmytro.realty.domain.RealtyCriteria;

import java.util.Set;

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
        switch (criteria.getProductType())
        {
            case APPARTMENT:
                criteriaString+= "&section=1972&";
        }

        return "";
    }

    @Override
    protected String location(Set<Location> locations) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected String rooms(int fromRooms, int toRooms) {
        String roomProperty = fromRooms == 5 ? "5-100" : fromRooms + "";
        if (fromRooms != toRooms) {
            for (int i = fromRooms + 1; i <= toRooms; i++)
                roomProperty += "%2C" + i;
            roomProperty += toRooms == 5 ? "5-100" : toRooms + "";
        }

        return "&" ;//+ parametrize(properties.getProperty("FILTER_ROOMS"), fromRooms, toRooms);
    }

    @Override
    protected String price(int fromPrice, int toPrice) {
        return "&price=от+"+fromPrice+"+до+"+toPrice+"&currency=2";
    }
}
