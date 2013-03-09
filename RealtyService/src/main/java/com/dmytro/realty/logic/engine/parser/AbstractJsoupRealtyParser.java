package com.dmytro.realty.logic.engine.parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public abstract class AbstractJsoupRealtyParser implements IRealtyParser {

    @Override
    public Document getSource(String request) throws RealtyUnparsebleException {
	try {
	    URI uri = new URI(request);
	    return Jsoup.parse(uri.toURL(), 20000);
	} catch (URISyntaxException | IOException e) {
	    throw new RealtyUnparsebleException("Can't parse the link: " + request);
	}
    }
}
