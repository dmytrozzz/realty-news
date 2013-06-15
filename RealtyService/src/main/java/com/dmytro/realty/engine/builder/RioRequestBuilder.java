package com.dmytro.realty.engine.builder;

import com.dmytro.realty.domain.Location;
import com.dmytro.realty.domain.Product;
import com.dmytro.realty.domain.RealtyCriteria;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 15.06.13
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class RioRequestBuilder extends ARealtyRequestBuilder {

    private RealtyCriteria realtyCriteria;

    @Override
    public String host() {
        return "http://dom.ria.ua";
    }

    @Override
    protected String criteria(RealtyCriteria criteria) {
        realtyCriteria = criteria;
        String criteriaString = "/ru/search/?category=1";
        switch (criteria.getProductType()) {
            case APPARTMENT:
                criteriaString += "&realty_type=2";
                break;
            case ROOM:
                criteriaString += "&realty_type=3";
                break;
            case HOUSE:
                criteriaString = "/ru/search/?category=4&realty_type=5";
        }
        switch (criteria.getOperation()) {
            case BUY:
                return criteriaString + "&advert_type=1";
            case RENT:
                return criteriaString + "&advert_type=3";
        }
        return "";
    }

    @Override
    protected String location(Set<Location> locations) {
        return "&state_id=10&city_id=10&district_id=0";
    }

    @Override
    protected String price(int from, int to) {
        //240 - UAH, 239 - USA
        if (realtyCriteria.getOperation() == Product.Operation.BUY)
            return parametrize(
                    "&characteristic%5B234%5D%5Bfrom%5D={0}&characteristic%5B234%5D%5Bto%5D={1}&characteristic%5B242%5D=240", from, to);
        else if (realtyCriteria.getOperation() == Product.Operation.RENT)
            return parametrize(
                    "&characteristic%5B235%5D%5Bfrom%5D={0}&characteristic%5B235%5D%5Bto%5D={1}&characteristic%5B246%5D=244", from, to);
        return "";
    }

    @Override
    protected String rooms(int from, int to) {
        return parametrize("&characteristic%5B209%5D%5Bfrom%5D={0}&characteristic%5B209%5D%5Bto%5D={1}", from, to);
    }
}
