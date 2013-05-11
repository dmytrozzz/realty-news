package com.dmytro.realty.engine.parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.dmytro.realty.engine.builder.RealtyRequestBuilder;


public abstract class AbstractJsoupRealtyParser implements IRealtyParser {
	
	protected RealtyRequestBuilder requestBuilder;
	
	public void setRequestBuilder(RealtyRequestBuilder requestBuilder) {
		this.requestBuilder = requestBuilder;
	}

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
