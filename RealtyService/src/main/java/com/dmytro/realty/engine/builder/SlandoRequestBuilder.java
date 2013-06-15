package com.dmytro.realty.engine.builder;

import com.dmytro.realty.domain.Location;
import com.dmytro.realty.domain.RealtyCriteria;
import org.jsoup.HttpStatusException;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 15.06.13
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class SlandoRequestBuilder extends ARealtyRequestBuilder {

    @Override
    public String host() {
        return "http://kiev.ko.slando.ua";
    }

    @Override
    protected String criteria(RealtyCriteria criteria) {
        String criteriaString = "/nedvizhimost/";
        switch (criteria.getOperation()) {
            case BUY:
                criteriaString += "prodazha";
                break;
            case RENT:
                criteriaString += "arenda";
                break;
        }
        criteriaString+="-";
        switch (criteria.getProductType()) {
            case ROOM:
                criteriaString += "komnat";
                break;
            case APPARTMENT:
                criteriaString += "kvartir";
                break;
            case HOUSE:
                criteriaString += "domov";
                break;
        }
        return criteriaString + "/?";
    }

    @Override
    protected String location(Set<Location> locations) {
        String s = "";
        for (Location l : locations)
            s += "&search%5Bdistrict_id%5D%5B0%5D=" + l.getLocation().getSlandoIndex();
        return s;
    }

    @Override
    protected String rooms(int from, int to) {
        return "&" + parametrize(
                "search%5Bfilter_float_number_of_rooms%3Afrom%5D={0}&search%5Bfilter_float_number_of_rooms%3Ato%5D={1}", from, to);
    }

    @Override
    protected String price(int from, int to) {
        return "&" + parametrize(
                "search%5Bfilter_float_price%3Afrom%5D={0}&search%5Bfilter_float_price%3Ato%5D={1}", from, to) + "&currency=UAH";
    }
}
