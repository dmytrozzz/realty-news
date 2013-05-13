package com.dmytro.realty.engine.builder;

public class AvisoRequestBuilder extends DefaultRealtyRequestBuilder {

	@Override
	protected String rooms(int fromRooms, int toRooms) {
		String rooms = "";
		for (int i = fromRooms; i < toRooms; i++) {
			rooms += "&" + parametrize(properties.getProperty("FILTER_ROOMS"), i);
		}
		return rooms;
	}

}
