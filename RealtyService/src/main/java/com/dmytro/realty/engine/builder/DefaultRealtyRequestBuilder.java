package com.dmytro.realty.engine.builder;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.dmytro.realty.domain.Location;
import com.dmytro.realty.domain.Product;
import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyParameters;

public class DefaultRealtyRequestBuilder {

	protected Properties properties;

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties;
	}

	public String buildRequest(RealtyCriteria criteria) {
		return getSearchHost() + "/" + getCriteriaPart(criteria.getProductType(), criteria.getOperation())
				+ getParameters(criteria, criteria.getParameters()) + location(criteria.getLocations())+properties.getProperty("END");
	}

	private String getParameters(RealtyCriteria criteria, RealtyParameters parameters) {
		return price(parameters.getFromPrice(), parameters.getToPrice())
				+ (criteria.getProductType() != Product.Type.ROOM ? rooms(parameters.getFromRooms(),
						parameters.getToRooms()) : "");
	}

	private String getCriteriaPart(Product.Type productType, Product.Operation operation) {
		return properties.getProperty(productType + "_" + operation);
	}

	private String getSearchHost() {
		return properties.getProperty("host");
	}

	protected String parametrize(String source, Object... parameters) {
		String target = new String(source);
		for (int i = 0; target.contains("{" + i + "}"); i++) {
			target = target.replace("{" + i + "}", parameters[i] + "");
		}
		return target;
	}

    protected String location(Set<Location> locations) {
        String s = "&search%5Bdistrict_id%5D%5B0%5D=3&search%5Bdistrict_id%5D%5B1%5D=5";
        for(Location l : locations)
            s+="&search%5Bdistrict_id%5D%5B0%5D="+l.getLocation().getSlandoIndex();
        return s;
    }

	protected String rooms(int fromRooms, int toRooms){
		return "&" + parametrize(properties.getProperty("FILTER_ROOMS"), fromRooms, toRooms);
	}

	protected String price(int fromPrice, int toPrice) {
		return properties.getProperty("BEGIN")
				+ parametrize(properties.getProperty("FILTER_PRICE"), fromPrice, toPrice);
	}
}