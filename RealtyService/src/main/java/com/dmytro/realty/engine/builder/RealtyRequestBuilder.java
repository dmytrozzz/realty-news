package com.dmytro.realty.engine.builder;

import java.lang.annotation.Retention;
import java.util.Properties;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.search.enums.ProductType;

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
		request += properties.getProperty("BEGIN")
				+ parametrize(properties.getProperty("FILTER_PRICE"), criteria
						.getParameters().getFromPrice(), criteria
						.getParameters().getToPrice());
		// rooms
		if (criteria.getProductType() != ProductType.ROOM) {
			if (properties.getProperty("NAME").equals("AVISO"))
				request += getRooms(criteria.getParameters().getFromRooms(),
						criteria.getParameters().getToRooms());
			else if (properties.getProperty("NAME").equals("RIELTOR")) {
				request += getRealtorRooms(criteria.getParameters()
						.getFromRooms(), criteria.getParameters().getToRooms());
			} else
				request += "&"
						+ parametrize(properties.getProperty("FILTER_ROOMS"),
								criteria.getParameters().getFromRooms(),
								criteria.getParameters().getToRooms());
		}

		if (properties.getProperty("CURRENCY") != null)
			request += "&" + properties.getProperty("CURRENCY");

		// end
		if (properties.getProperty("END") != null)
			request += properties.getProperty("END");

		return request;
	}

	private String getRealtorRooms(int from, int to) {
		String rooms = "";
		for (int i = from; i < to-1; i++) {
			rooms += i + ",";
		}
		rooms += to;
		return "&" + parametrize(properties.getProperty("FILTER_ROOMS"), rooms);
	}

	private String getRooms(int from, int to) {
		String rooms = "";
		for (int i = from; i < to; i++) {
			rooms += "&"
					+ parametrize(properties.getProperty("FILTER_ROOMS"), i);
		}
		return rooms;
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