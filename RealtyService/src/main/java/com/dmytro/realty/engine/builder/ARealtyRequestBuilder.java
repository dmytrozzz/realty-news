package com.dmytro.realty.engine.builder;

import com.dmytro.realty.domain.Location;
import com.dmytro.realty.domain.Product;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyParameters;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 15.06.13
 * Time: 12:28
 * To change this template use File | Settings | File Templates.
 */
public abstract class ARealtyRequestBuilder {

    public String buildRequest(RealtyCriteria criteria) {
        return host() + criteria(criteria) + parameters(criteria, criteria.getParameters())
                + location(criteria.getLocations()) + end();
    }

    private String parameters(RealtyCriteria criteria, RealtyParameters parameters) {
        return (criteria.getProductType() == Product.Type.APPARTMENT ? rooms(parameters.getFromRooms(),
                parameters.getToRooms()) : "") + price(parameters.getFromPrice(), parameters.getToPrice());
    }

    public abstract String host();

    protected abstract String criteria(RealtyCriteria criteria);

    protected abstract String location(Set<Location> locations);

    protected abstract String price(int from, int to);

    protected abstract String rooms(int from, int to);

    protected String end() {
        return "";
    }

    public static String parametrize(String source, Object... parameters) {
        String target = new String(source);
        for (int i = 0; target.contains("{" + i + "}"); i++) {
            target = target.replace("{" + i + "}", parameters[i] + "");
        }
        return target;
    }
}
