package com.dmytro.realty.engine.builder;

public class RealtorRequestBuilder extends DefaultRealtyRequestBuilder {

	@Override
	protected String rooms(int fromRooms, int toRooms) {
		String rooms = "";
		for (int i = fromRooms; i < toRooms - 1; i++) {
			rooms += i + ",";
		}
		rooms += toRooms;
		return "&" + parametrize(properties.getProperty("FILTER_ROOMS"), rooms);
	}

}
