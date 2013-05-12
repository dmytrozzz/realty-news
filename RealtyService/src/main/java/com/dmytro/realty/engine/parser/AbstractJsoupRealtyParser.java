package com.dmytro.realty.engine.parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.dmytro.realty.engine.RealtyOffer;
import com.dmytro.realty.engine.builder.RealtyRequestBuilder;

public abstract class AbstractJsoupRealtyParser implements IRealtyParser {

	protected RealtyRequestBuilder requestBuilder;

	public void setRequestBuilder(RealtyRequestBuilder requestBuilder) {
		this.requestBuilder = requestBuilder;
	}

	@Override
	public List<String> parseRequest(String request)
			throws RealtyUnparsebleException {
		List<String> links = new LinkedList<>();
		parseRequest(getSource(request), links);
		return links;
	}

	protected abstract void parseRequest(Document source, List<String> links)
			throws RealtyUnparsebleException;

	@Override
	public RealtyOffer parseOffer(String offerRequest)
			throws RealtyUnparsebleException {
		RealtyOffer offer = new RealtyOffer();
		offer.setLink(offerRequest);
		
		Document document = getSource(offerRequest);
		offer.setOfferContent(parseContent(document));
		offer.setOffender(parseOffender(document));		
		offer.setDate(parseDate(document));
		offer.setPhoneRef(parsePhone(document));
		offer.setPrice(parsePrice(document));
		
		return offer;
	}

	protected abstract String parsePrice(Document document);

	protected abstract String parsePhone(Document document);

	protected abstract String parseDate(Document document);

	protected abstract String parseOffender(Document document);

	protected abstract String parseContent(Document document);

	@Override
	public Document getSource(String request) throws RealtyUnparsebleException {
		try {
			URI uri = new URI(request);
			return Jsoup.parse(uri.toURL(), 20000);
		} catch (URISyntaxException | IOException e) {
			throw new RealtyUnparsebleException("Can't parse the link: "
					+ request);
		}
	}
}
