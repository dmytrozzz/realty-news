package com.dmytro.realty.engine.builder;

import com.dmytro.realty.domain.RealtyCriteria;
import com.dmytro.realty.domain.RealtyParameters;

public class SlandoCriteriaConverter implements RealtyCriteriaConverter {
    public static String host = "kiev.ko.slando.ua";
    public static String appartment_request = "/nedvizhimost/arenda-kvartir/";
    public static String roomRequest = "/nedvizhimost/arenda-komnat/";
    public static String imageSource = "/ajax/misc/phoneimage/";

    @Override
    public String buildRequest(RealtyCriteria criteria) {
	RealtyParameters args = criteria.getParameters();
	String request = protocol + host;
	switch (criteria.getProductType()) {
	case ROOM:
	    request += roomRequest + findRoomCriteria(args.getFromPrice(), args.getToPrice());
	    break;
	case APPARTMENT:
	    request += appartment_request + findAppartmentCriteria(args.getFromPrice(), args.getToPrice(), 2);
	    break;
	default:
	    break;
	}
	return request;
    }

    public static String createImageAddress(String id) {
	return protocol + host + imageSource + id + "/?nomobile=1";
    }

    public static String findAppartmentCriteria(int priceFrom, int priceTo, int rooms) {
	return "?search%5Bfilter_float_price%3Afrom%5D=" + priceFrom + "&search%5Bfilter_float_price%3Ato%5D="
		+ priceTo + "&search%5Bfilter_float_number_of_rooms%3Ato%5D=" + rooms
		+ "&search%5Bprivate_business%5D=private";
    }

    public static String findRoomCriteria(int priceFrom, int priceTo) {
	return "?search%5Bfilter_float_price%3Afrom%5D=" + priceFrom + "&search%5Bfilter_float_price%3Ato%5D="
		+ priceTo + "&search%5Bprivate_business%5D=private";
    }

}
