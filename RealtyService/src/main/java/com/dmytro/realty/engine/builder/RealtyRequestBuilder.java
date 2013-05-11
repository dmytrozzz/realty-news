package com.dmytro.realty.engine.builder;

import java.util.Properties;

import com.dmytro.realty.domain.RealtyCriteria;

public class RealtyRequestBuilder {

	private Properties properties;

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties;
	}

	public String buildRequest(RealtyCriteria criteria) {
		String request = buildReqularRequest()
				+ properties.getProperty(criteria.getProductType() + "_"
						+ criteria.getOperation());

		// add search parameters: price;
		request += "/?"
				+ parametrize(properties.getProperty("FILTER_PRICE"), criteria
						.getParameters().getFromPrice(), criteria
						.getParameters().getToPrice());
		// rooms
		request += "&"
				+ parametrize(properties.getProperty("FILTER_ROOMS"), criteria
						.getParameters().getFromRooms(), criteria
						.getParameters().getToRooms());

		// currency
		request += "&" + properties.getProperty("CURRENCY");

		return request;
	}

	public String buildReqularRequest() {
		return properties.getProperty("host") + "/";
	}

	private String parametrize(String source, Object... parameters) {
		String target = new String(source);
		for (int i = 0; target.contains("{" + i + "}"); i++) {
			target = target.replace("{" + i + "}", parameters[i] + "");
		}
		return target;
	}
}